package com.iacademia.zonal_desk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.Display;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.android.gms.signin.SignIn;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    AppUpdater appUpdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        appUpdater = new AppUpdater(this)
                .setDisplay(Display.NOTIFICATION)
                .setUpdateFrom(UpdateFrom.GITHUB)
                .setGitHubUserAndRepo("athulcs", "zonaldesk");
        appUpdater.start();
    }

    public void login(View view){
        //Verify Login credentials
        Intent zonalIntent = new Intent(this,CustomerActivity.class);
        startActivity(zonalIntent);
    }
    public void register(View view){
        Intent registerIntent = new Intent(this,RegisterActivity.class);
        startActivity(registerIntent);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,"Under Development",Toast.LENGTH_SHORT).show();
    }
}
