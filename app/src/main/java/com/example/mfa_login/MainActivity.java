package com.example.mfa_login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.mfa_login.Login_api;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private Button Login;
    private TextView testresult;
    public Login_api login;


    private class Loginapi extends AsyncTask<String,Void,String>
    {
        private Login_api login;

        @Override
        protected String doInBackground(String... strings) {

            String username = strings[0];
            String password = strings[1];
            login = new Login_api(username,password);
            return null;

        }

        @Override
        protected void onPostExecute(String response) {
            if(login.result.equals("true"))
            {
                SharedPreferences sh = getApplicationContext().getSharedPreferences("mypref",MODE_PRIVATE);
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("Username",Username.getText().toString());
                ed.putString("Password",Password.getText().toString());
                ed.commit();
                Username.setText("");
                Password.setText("");
                Intent i = new Intent(MainActivity.this,Fingerprint_MFA.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Username or Password incorrect",Toast.LENGTH_LONG).show();

            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        Login = (Button) findViewById(R.id.loginbutton);
        testresult = (TextView) findViewById(R.id.testresult);


    }

    public void Api_call(View view)
    {
        String user = Username.getText().toString();
        String password = Password.getText().toString();
        Loginapi obapi =  new Loginapi();
        obapi.execute(user,password);
    }


}
