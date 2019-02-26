package com.example.ac_twitterclone;


import android.content.Intent;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class TwitterUserActivity extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_user);









        FancyToast.makeText(this, "Welcome" + ParseUser.getCurrentUser().getUsername(), Toast.LENGTH_LONG, FancyToast.SUCCESS,false).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutUserItem:
                ParseUser.getCurrentUser().logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        Intent intent =new Intent(TwitterUserActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                break;
        }




        return super.onOptionsItemSelected(item);
    }
}
