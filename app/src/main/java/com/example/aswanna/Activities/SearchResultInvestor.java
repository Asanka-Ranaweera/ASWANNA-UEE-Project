package com.example.aswanna.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aswanna.Adapters.ProposalAdapter;
import com.example.aswanna.Model.FilterData;
import com.example.aswanna.Model.Proposal;
import com.example.aswanna.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class SearchResultInvestor extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProposalAdapter adapter;
    private List<Proposal> proposalList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_investor);

        FilterData filterData = (FilterData) getIntent().getSerializableExtra("filterData");
        String location= filterData.getSpinner1Value();
        String type= filterData.getSpinner2Value();
        String farmerLevel= filterData.getSpinner3Value();
        String min= filterData.getEditText1Value();
        String max= filterData.getEditText2Value();


// Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.searchresult); // Replace with your RecyclerView ID
        proposalList = new ArrayList<>();
       // adapter = new ProposalAdapter(proposalList);
// Set up your RecyclerView with the adapter and layout manager (e.g., LinearLayoutManager)
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Query the "proposals" collection based on filter criteria
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference proposalsRef = db.collection("proposals");

        // You can use a combination of whereEqualTo and whereEqualTo queries for filtering
        proposalsRef
                .whereEqualTo("location", location)
                .whereEqualTo("type", type)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Proposal proposal = document.toObject(Proposal.class);
                        proposalList.add(proposal);
                    }
                    adapter.notifyDataSetChanged(); // Notify the adapter to update the RecyclerView
                })
                .addOnFailureListener(e -> {
                    // Handle any errors that may occur during the query
                });



    }
}