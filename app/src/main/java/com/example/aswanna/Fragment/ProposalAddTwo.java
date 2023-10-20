package com.example.aswanna.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aswanna.Activities.ProposalAdd;
import com.example.aswanna.R;
import com.google.android.material.textfield.TextInputEditText;

public class ProposalAddTwo extends Fragment {
    @Nullable

    private String data1,data2,data3,data4;

    TextInputEditText pDescription,pFund,pExpected;



    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_proposal_add_two, container, false);

        ProposalAdd proposalAdd=(ProposalAdd) getActivity();

        pDescription=view.findViewById(R.id.projectDescription);
        pFund=view.findViewById(R.id.fund);
        pExpected=view.findViewById(R.id.expect);


        Button btn=proposalAdd.findViewById(R.id.nextButton);


        Bundle args = getArguments();
        if (args != null) {
             data1 = args.getString("pName");
             data2=args.getString("location");
             data3=args.getString("type");
             data4=args.getString("time");








        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your code to display a Toast message here.


               String description=pDescription.getText().toString();
               String  expected=pExpected.getText().toString();
               String fund=pFund.getText().toString();





                ProductAddThree productAddThree = new ProductAddThree();
                Bundle bundle = new Bundle();
                bundle.putString("pName", data1);
                bundle.putString("location", data2);
                bundle.putString("type", data3);
                bundle.putString("time", data4);
                bundle.putString("description", description);
                bundle.putString("expected", expected);
                bundle.putString("fund", fund);

                productAddThree.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.viewPager,productAddThree,null).addToBackStack(null).commit();




            }
        });





        return view;
    }
}
