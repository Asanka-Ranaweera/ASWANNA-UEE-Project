package com.example.aswanna.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.aswanna.Fragment.HomePageInvestorFragment;
import com.example.aswanna.Fragment.InvestorProfile;
import com.example.aswanna.R;

public class InvestorHome extends AppCompatActivity {
    ImageView menue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_home);
        menue=findViewById(R.id.imageView11);




        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView2, new InvestorProfile());
        transaction.commit();

        menue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.fragmentContainerView2, new InvestorProfile());
                transaction.commit();

            }
        });






    }
}