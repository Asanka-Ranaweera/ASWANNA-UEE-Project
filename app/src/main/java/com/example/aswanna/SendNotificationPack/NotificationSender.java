package com.example.aswanna.SendNotificationPack;

import android.widget.Toast;

import com.example.aswanna.Activities.MainActivity;
import com.example.aswanna.Model.InvestorPreference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationSender {

    public Data data;
    public String to;

    public NotificationSender(Data data, String to) {
        this.data = data;
        this.to = to;
    }

    public NotificationSender() {


//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful() && task.getResult() != null) {
//                        token = task.getResult();
//
//                        t1.setText(token);
//
//
//                        FirebaseFirestore db = FirebaseFirestore.getInstance();
//                        InvestorPreference investorPreference=new InvestorPreference(token,"Polonnaruwa","Crop Production");
//
//// Create a User object with the user's data
//                        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
//
//                        CollectionReference proposalsCollection = firestore.collection("investorPreference");
//                        String documentId = proposalsCollection.document().getId();
//
//// Add or update the document in Firestore
//                        proposalsCollection.document(documentId)
//                                .set(investorPreference)
//                                .addOnSuccessListener(aVoid -> {
//
//                                    Toast.makeText(MainActivity.this, "Data has been successfully stored in Firestore", Toast.LENGTH_SHORT).show();
//
//                                })
//                                .addOnFailureListener(e -> {
//                                    Toast.makeText(MainActivity.this, "Data could not be stored in Firestore. Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                });
//
//
//
//
//
//
//                    } else {
//                        // Handle the error if token retrieval fails.
//                    }
//                });



    }
}
