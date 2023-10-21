package com.example.aswanna.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import com.example.aswanna.Adapters.ProposalAdapter;
import com.example.aswanna.Model.FilterData;
import com.example.aswanna.Model.Proposal;
import com.example.aswanna.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot; // Change to DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchResultInvestor extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProposalAdapter adapter;
    private List<Proposal> proposals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_investor);

        // Get filter data from the intent
        FilterData filterData = (FilterData) getIntent().getSerializableExtra("filterData");
        String location = filterData.getSpinner1Value();
        String type = filterData.getSpinner2Value();
        String farmerLevel = filterData.getSpinner3Value();
        String min = filterData.getEditText1Value();
        String max = filterData.getEditText2Value();
       int newmin = Integer.parseInt(min);
       int newmax = Integer.parseInt(max);
    System.out.println(newmin);
        System.out.println(farmerLevel);
        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.searchresult); // Replace with your RecyclerView ID
        proposals = new ArrayList<>();
        adapter = new ProposalAdapter(proposals, new ProposalAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(Proposal proposal) {
                // Handle button click actions if needed
            }
        });

        // Set up your RecyclerView with the adapter and layout manager (e.g., LinearLayoutManager)
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference proposalsRef = db.collection("proposals");

        // Query the "proposals" collection based on filter criteria
        proposalsRef
                .whereEqualTo("projectLocation", location)
                .whereGreaterThanOrEqualTo("fundingRequired", newmin)
                .whereLessThanOrEqualTo("fundingRequired", newmax)
                .whereEqualTo("projectType", type)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot document : queryDocumentSnapshots.getDocuments()) {
                            Proposal proposal = document.toObject(Proposal.class);
                            // Fetch user details based on farmerID from the "user" collection
                            String farmerID = proposal.getFarmerID();
                            fetchFarmerLevelAndFilter(farmerID, farmerLevel, proposal);
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("FirestoreError", "Error fetching data: " + e.getMessage());
                });
    }

    private void fetchFarmerLevelAndFilter(String farmerID, String desiredLevel, Proposal proposal) {
        // Fetch user details from the "user" collection based on farmerID
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersRef = db.collection("users");

        usersRef.document(farmerID).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot userDoc) {
                        String farmerLevel = userDoc.getString("level");
                        if (farmerLevel != null && farmerLevel.equals(desiredLevel)) {

                            // The farmer's level matches the desired level, add the proposal to the list
                            proposals.add(proposal);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
