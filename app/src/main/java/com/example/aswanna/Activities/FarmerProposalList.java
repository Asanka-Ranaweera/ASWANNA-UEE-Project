package com.example.aswanna.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.aswanna.Adapters.ProposalAdapter;
import com.example.aswanna.Model.Proposal;
import com.example.aswanna.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FarmerProposalList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProposalAdapter proposalAdapter;
    private ArrayList<Proposal> proposalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_proposal_list);


        recyclerView = findViewById(R.id.proposalList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        proposalAdapter = new ProposalAdapter(proposalList);



        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        CollectionReference proposalsRef = firestore.collection("proposals");

        proposalsRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {



                            // Iterate through the documents and add them to the list
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Proposal proposal = document.toObject(Proposal.class);
                                proposalList.add(proposal);
                            }




                        recyclerView.setAdapter(proposalAdapter);




                    }

                });






    }
}