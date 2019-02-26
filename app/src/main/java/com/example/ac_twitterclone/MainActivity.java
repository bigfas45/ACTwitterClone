package com.example.ac_twitterclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    ui Comp
     private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignUp, btnLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ParseInstallation.getCurrentInstallation().saveInBackground();

        setTitle("Sign Up");

        edtEmail = findViewById(R.id.edtEmail);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp);
                }
                return false;
            }
        });

        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionToTwitterUserActivity();
        }


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case  R.id.btnSignUp:
                if (edtEmail.getText().toString().equals("") || edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")){
                    FancyToast.makeText(MainActivity.this, "Email, Username, Password is required ", Toast.LENGTH_LONG, FancyToast.INFO,true).show();


                }else {

                    final ParseUser appUser = new ParseUser();
                    appUser.setEmail(edtEmail.getText().toString());
                    appUser.setUsername(edtUsername.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up" + edtUsername.getText().toString());
                    progressDialog.show();


                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                FancyToast.makeText(MainActivity.this, appUser.getUsername() + " is Signed up", Toast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                transitionToTwitterUserActivity();
                            } else {
                                FancyToast.makeText(MainActivity.this, "There was an error " + e.getMessage(), Toast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }

                            progressDialog.dismiss();

                        }
                    });
                }





                break;



            case R.id.btnLogIn:

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

                break;



        }

    }
    public  void rootLayoutTapped(View view){
        try{
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void transitionToTwitterUserActivity(){
        Intent intent = new Intent(MainActivity.this, TwitterUserActivity.class);
        startActivity(intent);
        finish();
    }
}
