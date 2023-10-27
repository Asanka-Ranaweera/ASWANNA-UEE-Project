package com.example.aswanna.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.aswanna.Activities.NotificationManagement;
import com.example.aswanna.R;


public class InvestorProfile extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    Button manageN;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_investor_profile, container, false);

        manageN=view.findViewById(R.id.managenotification);


        manageN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your code to display a Toast message here.



                Intent intent = new Intent(getContext(), NotificationManagement.class);
                startActivity(intent);








            }
        });



        // Inflate the layout for this fragment
        return view;
    }
}