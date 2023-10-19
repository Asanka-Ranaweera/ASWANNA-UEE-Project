package com.example.aswanna.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;
import com.example.aswanna.Model.PreferenceManager;
import com.example.aswanna.Model.User;
import com.example.aswanna.databinding.ActivitySignInBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager= new PreferenceManager(getApplicationContext());
        if(preferenceManager.getBoolean(User.KEY_IS_SIGNED_IN)){
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListners();

    }
    private void setListners(){
        binding.signUptext.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class)));
        binding.signinBtn.setOnClickListener(v->{
            if(isValidSignInDetails()){
                signIn();
            }
        });
    }

    private void signIn(){
        loading(true);
        FirebaseFirestore database =FirebaseFirestore.getInstance();
        database.collection(User.KEY_COLLECTION_USERS)
                .whereEqualTo(User.KEY_EMAIL,binding.inputEmail.getText().toString())
                .whereEqualTo(User.KEY_PASSWORD,binding.inputPassword.getText().toString())
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful() && task.getResult() !=null
                    && task.getResult().getDocuments().size()>0){
                        DocumentSnapshot documentSnapshot =task.getResult().getDocuments().get(0);
                        preferenceManager.putBoolean(User.KEY_IS_SIGNED_IN,true);
                        preferenceManager.putString(User.KEY_USER_ID,documentSnapshot.getId());
                        preferenceManager.putString(User.KEY_NAME,documentSnapshot.getString(User.KEY_NAME));
                        preferenceManager.putString(User.KEY_IMAGE,documentSnapshot.getString(User.KEY_IMAGE));
                        showToast("Have a Nice Day " + preferenceManager.getString(User.KEY_NAME));
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {
                        loading(false);
                        showToast("Unable to Sign In.Please TryAgain");
                    }
                });
    }

    private void loading(Boolean isLoading){
        if(isLoading){
            binding.signinBtn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else {
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.signinBtn.setVisibility(View.VISIBLE);
        }

    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    private Boolean isValidSignInDetails(){
        if(binding.inputEmail.getText().toString().trim().isEmpty()){
            showToast("Please Enter Email Address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()) {
            showToast("Enter Valid Email Address");
            return false;
        } else if (binding.inputPassword.getText().toString().trim().isEmpty()) {
            showToast("Please Enter Your Password");
            return false;
        }else {
            return true;
        }
    }


}