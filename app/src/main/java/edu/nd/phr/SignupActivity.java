package edu.nd.phr;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class SignupActivity extends ActionBarActivity {
    public final static String emailAvailabilityURL = "http://m-health.cse.nd.edu:8000/phrService-0.0.1-SNAPSHOT/signup/signup/<email>";
    public final static String signupURL = "http://m-health.cse.nd.edu:8000/phrService-0.0.1-SNAPSHOT/signup/signup";
    //subclass to verify and send signup information in background
    private class SignupAPICall extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params)
        {
            EditText emailEdit = (EditText)findViewById(R.id.signup_email);
            String email = emailEdit.getText().toString();
            String body = "<email>"+email+"</email>";
            byte[] bytebody = body.getBytes();
            String URLcall = emailAvailabilityURL + email + "</email>";
            InputStream in;
            Log.i("SignupActivity", "The value of params[0] is:" + params[0]);
            try {
                URL url = new URL(URLcall);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type","application/json");
                try {
                    DataOutputStream wr =  new DataOutputStream(urlConnection.getOutputStream());
                    wr.write(bytebody);
                    in = new BufferedInputStream(urlConnection.getInputStream());
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
            byte[] contents = new byte[1024];
            int bytesRead;
            String strFileContents = null;
            try {
                while ((bytesRead = in.read(contents)) != -1) {
                    strFileContents = new String(contents, 0, bytesRead);
                }
            }
            catch (Exception e) {
                 e.printStackTrace();
                 return e.getMessage();
            }
            Log.i("SignupActivity","response to API call is: "+strFileContents);
            return strFileContents;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return true;
    }
    public void verifySignup(View view) {
        EditText firstnameEditText = (EditText)findViewById(R.id.signup_firstname);
        EditText lastnameEditText = (EditText)findViewById(R.id.signup_lastname);
        EditText emailEditText = (EditText)findViewById(R.id.signup_email);
        EditText passwordEditText = (EditText)findViewById(R.id.signup_password);
        String first = firstnameEditText.getText().toString();
        String last = lastnameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        //have String values for First Name, Last Name, Email, Password
        if (email != null && password != null) {
            String XMLBody = "<user><firstName>"+first+"</firstName><lastName>"+last+"</lastName><email>"+email+"</email><password>"+password+"</password></user>";
            new SignupAPICall().execute(XMLBody);
        }
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
