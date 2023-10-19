package com.example.aswanna.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aswanna.Activities.ProposalAdd;
import com.example.aswanna.Model.Proposal;
import com.example.aswanna.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Random;


public class ProductAddFour extends Fragment {

    private StorageReference storageReference;

    private ImageView img;

    private TextView proposalId,projectName;

    private String data1,data2,data3,data4,data5,data6,data7,downloadUrl1,downloadUrl2,PID;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_product_add_four, container, false);
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference proposalsCollection = firestore.collection("proposals");
        img=view.findViewById(R.id.proposalImage);
        proposalId=view.findViewById(R.id.propsalid);
       projectName=view.findViewById(R.id.productname);

        Random random = new Random();


        int lastTwoDigits = random.nextInt(100);

        // Combine with the first two digits "00"
        String PID = String.format("00%02d", lastTwoDigits);

        Bundle args = getArguments();

        if (args != null) {
            data1 = args.getString("pName");
            data2=args.getString("location");
            data3=args.getString("type");
            data4=args.getString("time");
            data5=args.getString("description");
            data6=args.getString("expected");
            data7=args.getString("fund");
            downloadUrl1=args.getString("imgUrlOne");
            downloadUrl2=args.getString("imgUrlTwo");

        }


          projectName.setText(data1);
       proposalId.setText("Proposal ID-"+PID);


         // Change the path to your image




        Glide.with(this).load(downloadUrl1).into(img);




        ProposalAdd proposalAdd=(ProposalAdd) getActivity();

        Button btn=proposalAdd.findViewById(R.id.nextButton);

        btn.setText("Pay & Submit");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your code to display a Toast message here.
                String farmerId="0002";
                String status="on";
                String documentId = proposalsCollection.document().getId();
                Proposal proposal = new Proposal(PID,documentId,farmerId,data1,data3,data2,data4,data5,data7,data6,downloadUrl1,downloadUrl2,status);


                proposalsCollection.document(documentId).set(proposal)
                        .addOnSuccessListener(aVoid -> {

                            Toast.makeText(requireContext(), "successfully insert", Toast.LENGTH_SHORT).show();

                            ProposalAddOne proposalAddTwo = new ProposalAddOne();

                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.viewPager,proposalAddTwo,null).addToBackStack(null).commit();


                        })
                        .addOnFailureListener(e -> {

                            Toast.makeText(requireContext(), "DataBase error", Toast.LENGTH_SHORT).show();


                        });







                ProposalAddOne proposalAddTwo = new ProposalAddOne();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.viewPager,proposalAddTwo,null).addToBackStack(null).commit();




            }
        });









        return view;
    }
}