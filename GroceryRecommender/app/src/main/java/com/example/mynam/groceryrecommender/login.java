package com.example.mynam.groceryrecommender;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText rEmail, rPass;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    int check = 0;
    Button toCompare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        rEmail = (EditText) findViewById(R.id.login_email);
        rPass = (EditText) findViewById(R.id.login_password);
        toCompare = (Button) findViewById(R.id.login_login) ;
        progressDialog = new ProgressDialog(this);

        toCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    public void signIn()
    {
        String email = rEmail.getText().toString();
        String pass = rPass.getText().toString();

        if(email.equals("") || pass.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Email or Password is blank!", Toast.LENGTH_LONG).show();
        }
        else{
            progressDialog.setMessage("Logging in please wait...");
            progressDialog.show();
            auth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.hide();
                                Toast.makeText(getApplicationContext(), "Sign In Succesful.", Toast.LENGTH_LONG).show();
                                check = 1;
                            }
                            else
                            {
                                progressDialog.hide();
                                Toast.makeText(getApplicationContext(), "Sign In Failed.", Toast.LENGTH_LONG).show();
                                check = 0;
                            }

                        }
                    });
        }
        if(check == 1)
        {
            Intent intent = new Intent(this, compare.class);
            startActivity(intent);
        }
    }
}