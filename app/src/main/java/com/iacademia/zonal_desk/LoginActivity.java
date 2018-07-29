package com.iacademia.zonal_desk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;

import java.util.concurrent.TimeUnit;


public class LoginActivity extends AppCompatActivity {

    AppUpdater appUpdater;
    EditText email;
    EditText pass;
    String result;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       email = findViewById(R.id.email_et);
       pass=findViewById(R.id.pass_et);
    }

    public void login(View view){
        //Verify Login credentials
        // BackgroundConnection backgroundConnection = new BackgroundConnection(this);
        BackgroundProcessLogin backgroundProcessLogin = new BackgroundProcessLogin(this);
        backgroundProcessLogin.execute("Login", email.getText().toString(), pass.getText().toString());
        Log.i("BackgroundLogin","OK");
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        result = backgroundProcessLogin.getResult();
        Log.i("Result",result);
        if (result.equals("Customer")) {  //Tip is wrong Try both always
            Toast.makeText(this, "Welcome " + result, Toast.LENGTH_SHORT).show();

            intent = new Intent(LoginActivity.this, CustomerDashboard.class);
            startActivity(intent);
        } else if (result.equals("Manager")) {
            Toast.makeText(this, "Welcome " + result, Toast.LENGTH_SHORT).show();
            intent = new Intent(LoginActivity.this, ManagementDashboard.class);
            startActivity(intent);

        } else if (result.equals("Engineer")) {
            Toast.makeText(this, "Welcome " + result, Toast.LENGTH_SHORT).show();
            intent = new Intent(LoginActivity.this, EngineersDashboard.class);
            startActivity(intent);

        } else {
            Toast.makeText(LoginActivity.this, "" + result, Toast.LENGTH_SHORT).show();
        }

    }
    public void register(View view){
        Intent registerIntent = new Intent(this,RegisterActivity.class);
        startActivity(registerIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.update:appUpdater.start();return true;
            default: return false;
        }
    }
}
