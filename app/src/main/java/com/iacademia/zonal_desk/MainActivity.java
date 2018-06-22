package com.iacademia.zonal_desk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view){
        //Verify Login credentials
        Intent zonalintent = new Intent(this,CustomerActivity.class);
        startActivity(zonalintent);
    }
}
