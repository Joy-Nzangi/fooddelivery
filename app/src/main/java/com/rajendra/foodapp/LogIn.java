package com.rajendra.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.rajendra.foodapp.newmodel.UserDetail;
import com.rajendra.foodapp.util.CustomToast;

import java.util.Timer;
import java.util.TimerTask;

import static com.rajendra.foodapp.R.layout.activity_log_in;

public class LogIn extends AppCompatActivity {
    EditText emailId, firstName, lastName, address, password;
    Button btnSignUp;
    TextView tvSignIn;

    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_log_in);
//       above was setContentView(R.layout.activity_main);


        emailId = findViewById(R.id.editTextEmail);
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        address = findViewById(R.id.editTextAddress);
        password = findViewById(R.id.editTextPassword);
        btnSignUp = findViewById(R.id.signUpButton);
        tvSignIn = findViewById(R.id.textViewSignIn);


        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            //        My on click listener
            public void onClick(View view) {
                final String email = emailId.getText().toString();
                final String fName = firstName.getText().toString();
                final String lName = lastName.getText().toString();
                final String add = address.getText().toString();
                final String pwd = password.getText().toString();

//                If empty show error
                if (email.isEmpty()) {
                    emailId.setError("Please provide email id");
                    emailId.requestFocus();
                } else if (fName.isEmpty()) {
                    firstName.setError("Please provide your first name");
                    firstName.requestFocus();
                } else if (lName.isEmpty()) {
                    lastName.setError("Please provide your last name");
                    lastName.requestFocus();
                } else if (add.isEmpty()) {
                    address.setError("Please provide your address");
                    address.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Please provide password");
                    password.requestFocus();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd)
                            .addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {
                                        CustomToast.createToast(LogIn.this,
                                                "SignUp Unsuccessful, Plaese Try Again!"
                                                        + task.getException().getMessage(), true);
                                    } else {
                                        UserDetail userDetail = new UserDetail(fName, lName, add);
                                        String uid = task.getResult().getUser().getUid();
                                        firebaseDatabase.getReference(uid).setValue(userDetail)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Intent intent = new Intent(LogIn.this,
                                                                HomeActivity.class);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        intent.putExtra("name", fName + " " + lName);
                                                        startActivity(intent);
                                                    }
                                                });
                                    }

                                }
                            });
                } else {
                    CustomToast.createToast(LogIn.this, "Error Occurred !", true);
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogIn.this, SignIn.class);
//                was SignIn not MainActivity
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
    }
}