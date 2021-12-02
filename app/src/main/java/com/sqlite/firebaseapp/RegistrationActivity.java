package com.sqlite.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
FirebaseAuth auth;
    EditText username,password;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
//        updateUI(currentUser);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        auth=FirebaseAuth.getInstance();
         username=findViewById(R.id.username);
         password=findViewById(R.id.password);
        Button btnregister=findViewById(R.id.btnregister);

        btnregister.setOnClickListener(v->{
            createUser();
        });
    }

    public void createUser(){
        auth.createUserWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Toast.makeText(this, "User Created Successfully", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}