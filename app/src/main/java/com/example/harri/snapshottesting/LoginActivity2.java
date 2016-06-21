package com.example.harri.snapshottesting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity2 extends AppCompatActivity {

    private static final String TAG="LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.inputEmail) EditText emailText;
    @InjectView(R.id.inputPassword)EditText passwordText;
    @InjectView(R.id.btnLogin) Button loginButton;
    @InjectView(R.id.linkSignup) TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.inject(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }

        });
    }

    public void login() {
        Log.d(TAG, "Login");
        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog loginProgressDialog = new ProgressDialog(LoginActivity2.this, R.style.MaterialTheme);
//        final ProgressDialog loginProgressDialog = new ProgressDialog(LoginActivity2.this);
        loginProgressDialog.setIndeterminate(true);
        loginProgressDialog.setMessage("Authenticating...");
        loginProgressDialog.show();

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // either call success or fail
                        onLoginSuccess();
                        loginProgressDialog.dismiss();
                    }
                }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // overrides using back button to go to the main activity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(), LayoutActivity.class);
        startActivityForResult(intent, REQUEST_SIGNUP);
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean isValid = true;
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            isValid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 8) {
            passwordText.setError("at least 8 characters");
            isValid = false;
        } else {
            passwordText.setError(null);
        }

        return isValid;


    }

}
