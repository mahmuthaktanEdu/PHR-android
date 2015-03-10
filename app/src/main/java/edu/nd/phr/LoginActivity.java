package edu.nd.phr;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
        private static final String url = "http://m-health.cse.nd.edu:8000/phrService-0.0.1-SNAPSHOT/login/login";
    public void verifyLogin(View view) {

        EditText emailEditText = (EditText) findViewById(R.id.editText_email_address);
        EditText passwordEditText = (EditText) findViewById(R.id.editText_password);
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        boolean loginSuccess = false;

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("email", email));
        pairs.add(new BasicNameValuePair("password", password));
        post.setEntity(new UrlEncodedFormEntity(pairs));
        HttpResponse response = client.execute(post);

        //TODO: IF THE RESPONSE IS A SUCCESSFUL LOGIN, CHANGE LOGINSUCCESS TO TRUE
        //TODO: HOW DO I FIX THE UNHANDLED EXCEPTIONS IN THE TWO LINES ABOVE
        if (loginSuccess) {
            Intent i = new Intent(getApplicationContext(), LandingActivity.class);
            startActivity(i);
        }
        else {
            //display some textview below editTexts for failed login
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        post.setEntity(new UrlEncodedFormEntity(pairs));
        try {
            HttpResponse response = client.execute(post);
        }
        catch (UnsupportedEncodingException) {

        }
        catch (ClientProtocolException) {

        }
        catch (IOException) {

        }*/
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
