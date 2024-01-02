package com.example.myapplication;

import static com.example.myapplication.PreferencesUtility.*;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    Button login, signUp;
    CheckBox checkBoxRememberMe;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editTextEmail = findViewById(R.id.Email);
        editTextPassword = findViewById(R.id.Password);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signup);
        checkBoxRememberMe = findViewById(R.id.checkboxRememberMe);

        firebaseAuth = FirebaseAuth.getInstance();


        checkRememberMe();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }


                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    if (checkBoxRememberMe.isChecked()) {

                                        PreferencesUtility.saveLoginInfo(MainActivity.this, email, password, true);
                                    }
                                    navigateToHome();
                                } else {
                                    Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private void checkRememberMe() {
        Map<String, String> loginInfo = getSavedLoginInfo(this);
        if (loginInfo != null && Boolean.parseBoolean(loginInfo.get("rememberMe"))) {
            editTextEmail.setText(loginInfo.get("email"));
            editTextPassword.setText(loginInfo.get("password"));
            checkBoxRememberMe.setChecked(true);
            performAutoLogin(loginInfo.get("email"), loginInfo.get("password"));
        }
    }

    private void performAutoLogin(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            navigateToHome();
                        } else {
                            Toast.makeText(MainActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void navigateToHome() {
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
