package edu.nd.phr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.os.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends ActionBarActivity {
    public final static String apiURL = "http://m-health.cse.nd.edu:8000/phrService-0.0.1-SNAPSHOT/login/login";
    //private class to call the API
    private class CallAPI extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            String resultToDisplay = null;
            verificationResult result = null;
            InputStream in = null;
            //HTTP Get
            try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                return e.getMessage();
            }
            Log.i("LoginActivity","doInBackGround - response to API call is " + in);
            return resultToDisplay;
        }
        protected void onPostExecute(String result){

        }
    } //END CALL API
    private class verificationResult {
        public String verificationStatus;
        public String verificationResult;
    }
    public void verifyLogin(View view) {

        EditText emailEditText = (EditText) findViewById(R.id.editText_email_address);
        EditText passwordEditText = (EditText) findViewById(R.id.editText_password);
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email != null && password != null){
            String urlString = apiURL + "<user><email>" + email + "</email><password>" + password + "</password></user>";
            new CallAPI().execute(urlString);
        }
        /*boolean loginSuccess = false;
        //email and password hold the editText values, set loginSuccess to false
        HttpClient client = new DefaultHttpClient();
        HttpPut put = new HttpPut(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("email", email));
        pairs.add(new BasicNameValuePair("password", password));
        //put request with email and password
        HttpResponse response = null;
        //initial response is null
        //try executing the put request and get a response, catch some exceptions
        try {
            put.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
            response = client.execute(put);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //logging the response just to see what I get
        Log.i("LoginActivity", "LoginActivity.verifyLogin() - response is " + response);
        //TODO: IF THE RESPONSE IS A SUCCESSFUL LOGIN, CHANGE LOGINSUCCESS TO TRUE

        if (loginSuccess) {
            Intent i = new Intent(getApplicationContext(), LandingActivity.class);
            startActivity(i);
        }
        else {
            //display some textview below editTexts for failed login
        }
        */
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
