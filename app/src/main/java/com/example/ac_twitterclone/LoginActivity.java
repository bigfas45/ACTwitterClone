package com.example.ac_twitterclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtLoginName, edtLoginPassword;
    private Button btnLoginActivity, btnSignupLoginActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        setTitle("Log IN");

        edtLoginName = findViewById(R.id.edtLoginName);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        btnLoginActivity = findViewById(R.id.btnLoginActivity);
        btnSignupLoginActivity = findViewById(R.id.btnSignupLoginActivity);

        btnLoginActivity.setOnClickListener(this);
        btnSignupLoginActivity.setOnClickListener(this);
        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLoginActivity:
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Signing up" + edtLoginName.getText().toString());
                progressDialog.show();

                ParseUser.logInInBackground(edtLoginName.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null){
                            FancyToast.makeText(LoginActivity.this, user.getUsername() + " is logged is successfully", Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            transitionToTwitterUserActivity();
                        }else{
                            FancyToast.makeText(LoginActivity.this, "There was an error " + e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();

                        }
                        progressDialog.dismiss();
                    }
                });

                break;

            case R.id.btnSignupLoginActivity:
                break;
        }
    }
    public  void layoutOnclickSigning(View view){
        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void transitionToTwitterUserActivity(){
        Intent intent = new Intent(LoginActivity.this, TwitterUserActivity.class);
        startActivity(intent);
        finish();
    }
}
