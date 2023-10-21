package com.example.aswanna.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.aswanna.Activities.FilterBottomSheetFragment;
import com.example.aswanna.Activities.InvestorPostView;
import com.example.aswanna.Adapters.ProposalAdapter;
import com.example.aswanna.Model.Proposal;
import com.example.aswanna.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageInvestorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageInvestorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String userSession="123";

    public HomePageInvestorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageInvestorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageInvestorFragment newInstance(String param1, String param2) {
        HomePageInvestorFragment fragment = new HomePageInvestorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page_investor, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView); // Replace with your RecyclerView ID
        ImageView filter=view.findViewById(R.id.imageView4);
        // Initialize Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference proposalsRef = db.collection("proposals"); // Replace with the actual Firestore collection name

        // Query Firestore for proposals
        proposalsRef.get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<Proposal> proposals = new ArrayList<>();
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                Proposal proposal = document.toObject(Proposal.class);
                // Fetch user details based on farmerID from another Firestore collection
                CollectionReference usersRef = db.collection("users"); // Replace with the actual Firestore collection name

                    // Assuming "name" and "profileImage" are the field names in the user document


                    // Set the user details in the Proposal object


                    proposals.add(proposal);

                    // Notify the adapter when all data is retrieved
                    if (proposals.size() == queryDocumentSnapshots.size()) {
                        ProposalAdapter adapter = new ProposalAdapter(proposals, new ProposalAdapter.OnButtonClickListener() {
                            @Override
                            public void onButtonClick(Proposal proposal) {
                                // Handle the button click here
                                // You can start a new activity or perform any action you need
                                Intent intent = new Intent(getActivity(), InvestorPostView.class);
                                // Pass the proposal data to the new activity
                                intent.putExtra("proposal",proposal );



                                startActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }


            }

        });
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilterBottomSheet();
            }
        });
        return view;

    }
        public void openFilterBottomSheet() {
            FilterBottomSheetFragment bottomSheetFragment = new FilterBottomSheetFragment();
            bottomSheetFragment.show(getParentFragmentManager(), bottomSheetFragment.getTag());
        }
}