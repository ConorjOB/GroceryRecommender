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

public class register extends AppCompatActivity {

    EditText rEmail, rPass;
    Button toCompare;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        rEmail = (EditText) findViewById(R.id.register_email);
        rPass = (EditText) findViewById(R.id.register_password);
        toCompare = (Button) findViewById(R.id.register_register);
        progressDialog = new ProgressDialog(this);

        toCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    public void signUp()
    {
        String email = rEmail.getText().toString();
        String pass = rPass.getText().toString();

        if(email.equals("") || pass.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Email or Password is blank!", Toast.LENGTH_LONG).show();
        }
        else{
            progressDialog.setMessage("Registering please wait...");
            progressDialog.show();
            auth.createUserWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                progressDialog.hide();
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                                check = 1;
                            }
                            else
                            {
                                progressDialog.hide();
                                Toast.makeText(getApplicationContext(), "Register unsuccessful", Toast.LENGTH_LONG).show();
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

