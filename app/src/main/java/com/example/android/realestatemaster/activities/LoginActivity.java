package com.example.android.realestatemaster.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.android.realestatemaster.HelperClasses.Utilities;
import com.example.android.realestatemaster.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by 100043392 on 13-Jan-19.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText emailEt,passwordEt;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        emailEt = findViewById(R.id.login_emailid);
        passwordEt = findViewById(R.id.login_password);
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        };

    }
    public void login(View view){
        signIn();

    }
    public void signIn(){
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        if(!(TextUtils.isEmpty(email)||TextUtils.isEmpty(password))){
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Utilities.showToast(getApplicationContext(),"Congratulations");
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                    else{
                        Utilities.showToast(getApplicationContext(),"Authentication Failed");
                    }
                }
            });
        }
        else{
            Utilities.showToast(this,"Empty field");
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.signOut();
    }

    public void gotoSignupPage(View view) {
        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
    }
}
