package com.sqlite.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
FirebaseAuth auth;
EditText login_email,login_password;
Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth=FirebaseAuth.getInstance();

        login_email=findViewById(R.id.login_email);
        login_password=findViewById(R.id.login_password);
        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(v->{
            login();
        });
    }

    public  void login(){
        auth.signInWithEmailAndPassword(login_email.getText().toString(),login_password.getText().toString()).addOnCompleteListener(task -> {

            if(task.isSuccessful()){
                Intent intent=new Intent(this,MainActivity.class);
                              startActivity(intent);
            }
            else{
                Toast.makeText(this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}