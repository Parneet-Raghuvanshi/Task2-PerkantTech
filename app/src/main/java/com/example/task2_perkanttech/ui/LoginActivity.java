package com.example.task2_perkanttech.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.example.task2_perkanttech.R;
import com.example.task2_perkanttech.helpers.CustomSnacks;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout userNameInput,passwordInput;
    private Button loginButton;
    boolean doubleBackToExitPressedOnce = false;
    private CustomSnacks customSnacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameInput = findViewById(R.id.mail_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_btn);
        customSnacks = new CustomSnacks();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    void sendRequest(String userName, String password){
        final View view = findViewById(android.R.id.content);
        if (userName.equals("harsh") && password.equals("qwerty01$0(")) {
            Intent intent = new Intent(LoginActivity.this,Dashboard.class);
            intent.putExtra("ID","harsh");
            startActivity(intent);
            finish();
        }
        else if (userName.equals("nikita") && password.equals("asdfgh99A@")){
            Intent intent = new Intent(LoginActivity.this,Dashboard.class);
            intent.putExtra("ID","nikita");
            startActivity(intent);
            finish();
        }
        else {
            customSnacks.warnSnack(view,"Username or Password is Incorrect!");
        }
    }

    void validate() {
        final View view = findViewById(android.R.id.content);
        String userName = userNameInput.getEditText().getText().toString().trim();
        String password = passwordInput.getEditText().getText().toString().trim();

        if (userName.isEmpty()) {
            customSnacks.failSnack(view,"UserName can't be empty!");
            userNameInput.getEditText().requestFocus();
        }
        else if (userName.length()>20) {
            customSnacks.failSnack(view,"UserName is too long!");
            userNameInput.getEditText().requestFocus();
        }
        else if (password.isEmpty()){
            customSnacks.failSnack(view,"Password field can't be empty!");
            passwordInput.getEditText().requestFocus();
        }
        else if (password.length()>20){
            customSnacks.failSnack(view,"Password is too long!");
            passwordInput.getEditText().requestFocus();
        }
        else {
           sendRequest(userName,password);
        }
    }

    @Override
    public void onBackPressed() {
        View view = findViewById(android.R.id.content);
        if (doubleBackToExitPressedOnce) {
            finish();
        }

        this.doubleBackToExitPressedOnce = true;
        customSnacks.infoSnack(view,"Press back again to exit!");

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}