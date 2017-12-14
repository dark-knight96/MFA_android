package com.example.mfa_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;
import com.example.mfa_login.delCode;

import java.util.Random;
import java.util.UUID;

import com.example.mfa_login.Code_api;

public class Code_activity extends AppCompatActivity {

    private String SecretCode;
    private TextView CodeContent;
    private String username;

    public class CodeClass extends AsyncTask <String,Void,String>
    {
        private Code_api code_api;
        private Intent i;
        private String result;
        private delcode_call del;

        @Override
        protected String doInBackground(String... Strings) {
            String Username= Strings[0];
            String Sent_code_to_api = Strings[1];
            code_api = new Code_api(Username,Sent_code_to_api);
            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            try {
                Thread.sleep(40000);
            }catch(Exception e)
            {
                e.printStackTrace();
            }

            try {
                del = new delcode_call();
                this.result = del.execute(username).get();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            if(this.result.equals("true"))
            {
                Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_LONG);
                i = new Intent(Code_activity.this,Fingerprint_MFA.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Code Expired",Toast.LENGTH_LONG).show();
                i = new Intent(Code_activity.this,Fingerprint_MFA.class);
                startActivity(i);
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_activity);

        CodeContent = (TextView) findViewById(R.id.CodeContent);
        SharedPreferences sh = getSharedPreferences("mypref",MODE_PRIVATE);
        username = sh.getString("Username",null);

        this.SecretCode = this.Code_api_generate();
        CodeContent.setText(this.SecretCode);

        // add code to the db
        CodeClass c = new CodeClass();
        c.execute(username,SecretCode);          //change after test


    }

    protected String Code_api_generate()
    {
        Random rand = new Random();
        String source ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        for (int i=0;i<5;i++)
        {
            int randomindex = rand.nextInt(source.length());
            code.append(source.charAt(randomindex));
        }
        return code.toString();

    }
}
