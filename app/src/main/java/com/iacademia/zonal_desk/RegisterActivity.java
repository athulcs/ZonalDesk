package com.iacademia.zonal_desk;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText email;
    EditText pass;
    EditText confpass;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.register_name_et);
        phone=findViewById(R.id.register_phno_et);
        email=findViewById(R.id.register_email_et);
        pass=findViewById(R.id.register_pass_et);
        confpass = findViewById(R.id.register_conf_pass_et);
    }

    void dbInsert(View view) {

        if (phone.getText().toString().length() != 10) {
            phone.setError("Invalid phone number");
        } else if (!validateEmail(email.getText().toString())) {
            email.setError("Invalid email address");
        } else if (!validatePassword(pass.getText().toString())) {
            pass.setError("Password must contain atleast 6 characters");
        } else if (!(pass.getText().toString().equals(confpass.getText().toString()))) {
            confpass.setError("Password does not match");
        } else {
            phone.setError(null);
            email.setError(null);
            pass.setError(null);
            confpass.setError(null);

            BackgroundProcessUserCheck backgroundProcessUserCheck = new BackgroundProcessUserCheck(this);
            backgroundProcessUserCheck.execute("UserCheck", phone.getText().toString(), email.getText().toString());

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String result = backgroundProcessUserCheck.getResult();


            if (result.equals("No")) {
                Toast.makeText(this, "EmailID / Phone already registered!", Toast.LENGTH_LONG).show();
                AlertDialog alertDialog;
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Registration Error");
                alertDialog.setMessage("EmailID / Phone is already registered!");
                alertDialog.show();
                //System.exit(0);
            } else {


                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                BackgroundConnection backgroundConnection = new BackgroundConnection(this);
                backgroundConnection.execute("Register",name.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString(),
                        pass.getText().toString());

                    name.setText("");
                    phone.setText("");
                    email.setText("");
                    pass.setText("");
                    confpass.setText("");

                    Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                    finish();

            }


        }

    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

}
