package com.example.aswanna.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aswanna.Fragment.ProductAddThree;
import com.example.aswanna.Fragment.ProposalAddOne;
import com.example.aswanna.Fragment.ProposalAddTwo;
import com.example.aswanna.R;

public class ProposalAdd extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposal_add);




        getSupportFragmentManager().beginTransaction().add(R.id.viewPager,new ProposalAddOne()).commit();


       // final StepPagerAdapter adapter = new StepPagerAdapter(getSupportFragmentManager());
        //viewPager.setAdapter(adapter);

//        nextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int nextStep = viewPager.getCurrentItem() + 1;
//                if (nextStep < adapter.getCount()) {
//                    viewPager.setCurrentItem(nextStep);
//                } else {
//                    // Handle any action to finish the process when on the last step
//                }
//            }
//        });



    }

//    @Override
//    public void onBackPressed() {
//        // Prevent going back when the back button is pressed
//        int currentItem = viewPager.getCurrentItem();
//        if (currentItem > 0) {
//            viewPager.setCurrentItem(currentItem - 1);
//        } else {
//            super.onBackPressed();
//        }
//    }

//    private class StepPagerAdapter extends FragmentPagerAdapter {
//        public StepPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            switch (position) {
//                case 0:
//                    return new ProposalAddOne();
//                case 1:
//                    return new ProposalAddTwo();
//                case 2:
//                    return new ProductAddThree();
//                default:
//                    return null;
//            }
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//    }
}