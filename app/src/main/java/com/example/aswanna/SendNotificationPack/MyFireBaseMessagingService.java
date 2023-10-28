package com.example.aswanna.SendNotificationPack;


import android.Manifest;

import android.annotation.SuppressLint;
import android.app.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Bitmap;
import android.graphics.Color;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.example.aswanna.Activities.ChatActivity_FarmerSide;

import com.example.aswanna.Model.UserRetrive;

import com.bumptech.glide.Glide;
import com.example.aswanna.Activities.InvestorPostView;
import com.example.aswanna.Model.PreferenceManager;
import com.example.aswanna.Model.User;

import com.example.aswanna.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.storage.StorageReference;

import java.util.Random;

import java.util.Random;

public class MyFireBaseMessagingService extends FirebaseMessagingService {

    NotificationManager notificationManager;
    Notification notification;


     PreferenceManager preferenceManager;





    String title,message,imageUrl,location,price;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);

        preferenceManager = new PreferenceManager(this);
        title=remoteMessage.getData().get("projectName");
        message=remoteMessage.getData().get("projectType");
        imageUrl=remoteMessage.getData().get("imageUrl");
        location=remoteMessage.getData().get("location");
        price=remoteMessage.getData().get("price");

        NormalNotification();


    }


    private  void NormalNotification(){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        Intent actionIntent = new Intent(this, InvestorPostView.class);
        actionIntent.putExtra("your_key", "your_value"); // You can pass data to the action

// Create a PendingIntent for the action
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this, 0, actionIntent, PendingIntent.FLAG_UPDATE_CURRENT);

// Create an action and add it to the notification
        NotificationCompat.Action action = new NotificationCompat.Action(
                R.drawable.aswanna_logo, // Icon for the action
                "View Now", // Title of the action
                actionPendingIntent
        );

        Intent cancelIntent = new Intent(this, InvestorPostView.class);
        cancelIntent.setAction("cancel_action"); // Use a unique action string
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast(this, 0, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action cancelAction = new NotificationCompat.Action(
                R.drawable.aswanna_logo, // Icon for the cancel action
                "Dismiss", // Title of the action
                cancelPendingIntent
        );

        NotificationCompat.BigPictureStyle style =new NotificationCompat.BigPictureStyle();



        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getApplicationContext(), "channel_id")
                        .setSmallIcon(R.drawable.aswanna_logo)
                        .setContentTitle("Hi "+preferenceManager.getString(User.KEY_NAME)+",Invest Now Rs."+price+"/= For "+title)
                        .setColor(Color.GREEN)
                        .addAction(cancelAction)
                        .setContentText(message+ " Project In "+location)
                        .setSubText("New Notification From Asswanna")
                        .addAction(action);



        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();

        try {
            Bitmap largeBitmap = Glide.with(this)
                    .asBitmap()
                    .load(imageUrl)
                    .submit()
                    .get(); // This fetches the image synchronously, consider using a background thread for this operation.

            bigPictureStyle.bigPicture(largeBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        builder.setStyle(bigPictureStyle);




        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());






        //asanka

        UserRetrive userRetrive = new UserRetrive();
        userRetrive.id = remoteMessage.getData().get(User.KEY_USER_ID);
        userRetrive.name = remoteMessage.getData().get(User.KEY_NAME);
        userRetrive.token = remoteMessage.getData().get(User.KEY_FCM_TOKEN);

        int notificationId = new Random().nextInt();
        String channelId = "chat_message";

        Intent intent = new Intent(this, ChatActivity_FarmerSide.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(User.KEY_USER, userRetrive);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this, channelId);
        builder1.setSmallIcon(R.drawable.aswanna_logo);
        builder1.setContentTitle(userRetrive.name);
        builder1.setContentText(remoteMessage.getData().get(User.KEY_MESSAGE));
        builder1.setStyle(new NotificationCompat.BigTextStyle().bigText(
                remoteMessage.getData().get(User.KEY_MESSAGE)
        ));
        builder1.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder1.setContentIntent(pendingIntent);
        builder1.setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Chat Message";
            String channelDescription = "This notification channel is used for chat message notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManagerCompat.notify(notificationId, builder1.build());





    }






}
