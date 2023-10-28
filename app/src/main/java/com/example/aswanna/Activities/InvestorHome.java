package com.example.aswanna.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.aswanna.Fragment.HomePageInvestorFragment;
import com.example.aswanna.Massage_Main_Activity;
import com.example.aswanna.Model.PreferenceManager;
import com.example.aswanna.R;

public class InvestorHome extends AppCompatActivity {

    private PreferenceManager preferenceManager;
    ImageView chatInvestorSide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_home);

        preferenceManager =new PreferenceManager(getApplicationContext());
        chatInvestorSide = (ImageView) findViewById(R.id.chatInvestorSide); // Replace 'yourImageViewId' with the actual ID of your ImageView in the XML layout

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView2, new HomePageInvestorFragment());
        transaction.commit();

        chatInvestorSide.setOnClickListener(view -> {
                    // Create an Intent to start the Chat activity
                    Intent chatIntent = new Intent(InvestorHome.this, Massage_Main_Activity.class);
                    // Pass the farmerid as an extra to the Chat activity
//                    String farmerid1 = proposal.getFarmerID(); // Assuming you have the farmerid
//                    chatIntent.putExtra("farmerid", farmerid1);

                    // Start the Chat activity
                    startActivity(chatIntent);
                    finish();
                });

    }
}