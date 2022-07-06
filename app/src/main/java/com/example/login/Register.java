package com.example.login;

import androidx.activity.result.contract.ActivityResultContracts;
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

public class Register extends AppCompatActivity {

    EditText Emailr;
    EditText Passr;
    TextView tvLog;
    Button Regbtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Emailr = findViewById(R.id.Emailr);
        Passr = findViewById(R.id.Passr);
        tvLog = findViewById(R.id.tvLog);
        Regbtn = findViewById(R.id.Regbtn);

        mAuth = FirebaseAuth.getInstance();

        Regbtn.setOnClickListener(view -> {
            createUser();
        });

        tvLog.setOnClickListener(view -> {
            startActivity(new Intent(Register.this, Login.class));
        });

    }

    private void createUser(){
        String email = Emailr.getText().toString();
        String password = Passr.getText().toString();

        if (TextUtils.isEmpty(email)){
            Emailr.setError("Email cannot be empty!!!");
            Emailr.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            Passr.setError("Password cannot be empty!!!");
            Passr.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Register.this, "User Registered Successfully ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, Login.class));
                    }else{
                        Toast.makeText(Register.this, "Register Error: "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}