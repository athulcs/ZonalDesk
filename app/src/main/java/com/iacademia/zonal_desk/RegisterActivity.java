package com.iacademia.zonal_desk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    void dbInsert(View view){
        Toast.makeText(this,"Pushed to database successfully",Toast.LENGTH_SHORT).show();
    }
}
