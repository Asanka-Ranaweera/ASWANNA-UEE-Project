package com.example.aswanna.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aswanna.Model.Inquiry;
import com.example.aswanna.Model.PreferenceManager;
import com.example.aswanna.Model.Proposal;
import com.example.aswanna.Model.User;
import com.example.aswanna.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class InvestorPostView extends AppCompatActivity {

    private TextView userName, postDate,
            userLevel, projectName, pLocation,
            profit, pAmount,ptype,pDuration,pDetails;
    private Button investNow;

    private ImageView Fprofile,Pimage,chatFarmerBtn;


    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_post_view);

        ImageView back = findViewById(R.id.imageView8);
        ImageView home = findViewById(R.id.imageView16);
        chatFarmerBtn = findViewById(R.id.imageView17);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open the target activity
                Intent intent = new Intent(InvestorPostView.this, InvestorHome.class);

                // You can also pass extra data to the target activity if needed
                // intent.putExtra("key", value);

                // Start the target activity
                startActivity(intent);
            }
        }); home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to open the target activity
                Intent intent = new Intent(InvestorPostView.this, InvestorHome.class);

                // You can also pass extra data to the target activity if needed
                // intent.putExtra("key", value);

                // Start the target activity
                startActivity(intent);
            }
        });
        Proposal proposal = (Proposal) getIntent().getSerializableExtra("proposal");
        Intent intent = getIntent();

        preferenceManager = new PreferenceManager(this);


//        proposalImage = findViewById(R.id.pProjectImage);
//        profileImage = itemView.findViewById(R.id.pUserImage);
        userName = findViewById(R.id.ppostusername);
        postDate = findViewById(R.id.postdate);
        userLevel = findViewById(R.id.postLevel);
        projectName = findViewById(R.id.postprojectName);
        profit = findViewById(R.id.postProfit);
        pAmount = findViewById(R.id.postAmount);
        pLocation = findViewById(R.id.postlocation);
        ptype=findViewById(R.id.posttype);
        pDuration=findViewById(R.id.postDuration);
        pDetails=findViewById(R.id.postdetails);
        investNow = findViewById(R.id.button3); // Replace with your Button ID
        Fprofile=findViewById(R.id.imageView9);
        Pimage=findViewById(R.id.propsalimage);
        String projectId= proposal.getPID();
        String DocumentID= proposal.getDocumentID();
        String status= "pending";



        // Set the text of the TextView to the project name
        projectName.setText(preferenceManager.getString(User.KEY_NAME));
        userName.setText(proposal.getFarmerName());
        userLevel.setText("Level"+proposal.getFarmerLevel());
        profit.setText(proposal.getExpectedReturnsOnInvestment());
        pAmount.setText(String.valueOf(proposal.getFundingRequired()));
        pLocation.setText(proposal.getProjectLocation());
        ptype.setText(proposal.getProjectType());
        pDuration.setText(proposal.getProjectDurationInMonths());
        pDetails.setText(proposal.getProjectDescription());
        String farmerid=proposal.getFarmerID();
        String image=proposal.getImageOneLink();

        Glide.with(this).load(proposal.getImageOneLink()).into(Pimage);
        byte[] bytes = Base64.decode(proposal.getFarmerProfileImage(),Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        Fprofile.setImageBitmap(bitmap);

        chatFarmerBtn.setOnClickListener(view -> {
            // Create an Intent to start the Chat activity
            Intent chatIntent = new Intent(InvestorPostView.this, ChatActivity.class);

            // Pass the farmerid as an extra to the Chat activity
            String farmerid1 = proposal.getFarmerID(); // Assuming you have the farmerid
            chatIntent.putExtra("farmerid", farmerid1);

            // Start the Chat activity
            startActivity(chatIntent);
        });




        investNow.setOnClickListener(view -> {
            // Create an AlertDialog for confirmation
            AlertDialog.Builder builder = new AlertDialog.Builder(InvestorPostView.this);
            builder.setTitle("Confirm Inquiry");
            builder.setMessage("Are you sure you want to invest in this project?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Create an Inquiry object
                    Inquiry inquiry = new Inquiry();
                    inquiry.setFarmerName(userName.getText().toString());
                    inquiry.setFarmerID(proposal.getFarmerID());
                    inquiry.setProjectId(proposal.getPID());
                    inquiry.setProjectName(proposal.getProjectName());
                    inquiry.setInvestorId(preferenceManager.getString(User.KEY_USER_ID)); // Replace with the actual investor ID
                    inquiry.setStatus("pending");
                    String investorId=preferenceManager.getString(User.KEY_USER_ID);
                    inquiry.setInvestorId(investorId);
                    inquiry.setImage(proposal.getImageOneLink());

                    // Initialize Firestore and reference to the "Inquiries" collection
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference inquiriesRef = db.collection("Inquiries");
                    String documentId = inquiriesRef.document().getId();
                    inquiry.setDocumentID(documentId);

                    // Add the Inquiry object to the "Inquiries" collection
                    inquiriesRef.add(inquiry)
                            .addOnSuccessListener(documentReference -> {
                                // The Inquiry was successfully added to the "Inquiries" collection
                                // You can add any further actions or messages here
                                // For example, showing a success message or returning to a previous screen
                                Toast.makeText(InvestorPostView.this, "Investment confirmed", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(InvestorPostView.this, InvestorHome.class);

                                // You can also pass extra data to the target activity if needed
                                // intent.putExtra("key", value);

                                // Start the target activity
                                startActivity(intent);
                            })
                            .addOnFailureListener(e -> {
                                // Handle any errors that may occur during the insertion
                                // You can display an error message or log the error for debugging
                                Toast.makeText(InvestorPostView.this, "Failed to confirm investment", Toast.LENGTH_SHORT).show();
                            });

                    dialog.dismiss();
                }
            });


            //chat part


            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
   });

}
}