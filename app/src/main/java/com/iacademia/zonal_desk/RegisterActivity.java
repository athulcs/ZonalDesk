package com.iacademia.zonal_desk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.iacademia.zonal_desk.utils.BackgroundConnection;

public class RegisterActivity extends AppCompatActivity {
    EditText fname;
    EditText lname;
    EditText phone;
    EditText place;
    EditText email;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname=findViewById(R.id.register_fname_et);
        lname=findViewById(R.id.register_lname_et);
        phone=findViewById(R.id.register_phno_et);
        place=findViewById(R.id.register_place_et);
        email=findViewById(R.id.register_email_et);
        pass=findViewById(R.id.register_pass_et);
    }
    void dbInsert(View view){
        BackgroundConnection con = new BackgroundConnection(this);
        con.execute("Register",fname.getText().toString(),
                    lname.getText().toString(),
                    phone.getText().toString(),
                    place.getText().toString(),
                    email.getText().toString(),
                    pass.getText().toString());
    }

    public void clearData(){
        fname.setText("");
        lname.setText("");
        phone.setText("");
        place.setText("");
        email.setText("");
        pass.setText("");
    }
}
