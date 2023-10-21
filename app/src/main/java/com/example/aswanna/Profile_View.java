package com.example.aswanna;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.aswanna.Model.PreferenceManager;
import com.example.aswanna.Model.User;
import com.example.aswanna.databinding.ActivityFarmerHomePageBinding;
import com.example.aswanna.databinding.ActivityProfileViewBinding;


public class Profile_View extends AppCompatActivity {
    private ImageView profileImage;
    private PreferenceManager preferenceManager;
    private ActivityProfileViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        loadUserDetails();
    }
    private void loadUserDetails(){
        binding.fullNameTextView.setText(preferenceManager.getString(User.KEY_NAME));
        binding.levelTextView.setText(preferenceManager.getString(User.KEY_LEVEL));
        binding.emailTextView.setText(preferenceManager.getString(User.KEY_EMAIL));
        binding.phoneNoTextView.setText(preferenceManager.getString(User.KEY_PHONE_NO));
        binding.countTextView.setText(preferenceManager.getString(User.KEY_COUNT));
        byte[] bytes = Base64.decode(preferenceManager.getString(User.KEY_IMAGE),Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        binding.profileImage.setImageBitmap(bitmap);
    }
}