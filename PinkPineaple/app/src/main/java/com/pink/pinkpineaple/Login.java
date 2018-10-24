package com.pink.pinkpineaple;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseUser;
import com.pink.pinkpineaple.adapter.UsersChatAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = Login.class.getSimpleName();

    private FirebaseAuth mAuth;
    EditText email,password;
    Button login,register;
    String Email,Password;
    ProgressDialog mDialog;
    FirebaseUser mUser;
    FirebaseAuth.AuthStateListener mAuthListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.login_emailId);
        password = (EditText)findViewById(R.id.login_password);
        login = (Button)findViewById(R.id.login_here);
        register = (Button)findViewById(R.id.register_here);
        setAuthInstance();
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        mDialog = new ProgressDialog(this);
        mUser = FirebaseAuth.getInstance().getCurrentUser();


        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mUser!=null){
                    Intent intent = new Intent(Login.this,DashBoard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else {
                    Log.d(TAG,"AuthStateChange:LogOut");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListner!=null){
            mAuth.removeAuthStateListener(mAuthListner);
        }
    }

    private void setAuthInstance() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        if (v==login){
            onLogInUser();
        }else if (v==register){
            goToRegisterActivity();
        }
    }
    private void onLogInUser() {
        Email = email.getText().toString().trim();
        Password = password.getText().toString().trim();

        if (TextUtils.isEmpty(Email)){
            Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(Password)){
            Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }else if (Password.length()<6){
            Toast.makeText(Login.this,"Passwor must be greater then 6 digit",Toast.LENGTH_SHORT).show();
            return;
        }
        mDialog.setMessage("loging please wait...");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mDialog.dismiss();
                    checkIfEmailVerified();
                }else {
                    Toast.makeText(Login.this,"Error on login",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkIfEmailVerified() {
        FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
        boolean emailVerified = users.isEmailVerified();
        if(!emailVerified){
            Toast.makeText(this,"Verify the email address", Toast.LENGTH_LONG).show();
            mAuth.signOut();
            finish();
        } else{
            setUserOnline();
            goToMainActivity();
        }
    }

    private void setUserOnline() {
        if(mAuth.getCurrentUser()!=null ) {
            String userId = mAuth.getCurrentUser().getUid();
            FirebaseDatabase.getInstance()
                    .getReference().
                    child("users").
                    child(userId).
                    child("connection").
                    setValue(UsersChatAdapter.ONLINE);
        }
    }
    private void goToMainActivity() {
        Intent intent = new Intent(Login.this, DashBoard.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    private void goToRegisterActivity() {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}