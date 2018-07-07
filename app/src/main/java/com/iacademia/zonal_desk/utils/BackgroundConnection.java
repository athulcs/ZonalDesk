package com.iacademia.zonal_desk.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.iacademia.zonal_desk.RegisterActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundConnection extends AsyncTask<String,Void,String> {

    RegisterActivity registerActivity;

    public  BackgroundConnection(RegisterActivity activity){
        registerActivity = activity;
    }
    @Override
    protected String doInBackground(String... params) {
        String registerURL = "http://192.168.43.131/Register.php";
        String operation = params[0];
        if(operation.equals("Register")){
            try{

                String fname = params[1];
                String lname = params[2];
                String phone = params[3];
                String place = params[4];
                String email = params[5];
                String pass = params[6];

                URL url = new URL(registerURL);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String postData;
                postData = URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"
                        +URLEncoder.encode("lname","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")+"&"
                        +URLEncoder.encode("place","UTF-8")+"="+URLEncoder.encode(place,"UTF-8")+"&"
                        +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");

                bufferedWriter.write(postData);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    result+=line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(registerActivity.getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
        registerActivity.clearData();
        registerActivity.finish();
    }
}
