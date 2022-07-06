package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText EmailL;
    EditText PassL;
    TextView Regtv;
    Button Logbtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EmailL = findViewById(R.id.EmailL);
        PassL = findViewById(R.id.PassL);
        Regtv = findViewById(R.id.Regtv);
        Logbtn = findViewById(R.id.Logbtn);

        mAuth = FirebaseAuth.getInstance();

        Logbtn.setOnClickListener(view -> {
            LoginUser();
            startActivity(new Intent(Login.this, MainActivity.class));
        });
        Regtv.setOnClickListener(view-> {
            startActivity(new Intent(Login.this, Register.class));
        });
    }
    private void LoginUser(){
        String email = EmailL.getText().toString();
        String password = PassL.getText().toString();

        if (TextUtils.isEmpty(email)){
            EmailL.setError("Email cannot be empty!!!");
            EmailL.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            PassL.setError("Password cannot be empty!!!");
            PassL.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "User Log In Successfully ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Register.class));
                }else{
                        Toast.makeText(Login.this, "Register Error: "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
