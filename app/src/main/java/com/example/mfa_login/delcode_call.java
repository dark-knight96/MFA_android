package com.example.mfa_login;

import android.os.AsyncTask;
import com.example.mfa_login.Code_activity;

/**
 * Created by prashanth on 11/12/17.
 */

public class delcode_call extends AsyncTask<String,Void,String> {
    protected String result;

    @Override
    protected String doInBackground(String... strings) {

        delCode del = new delCode(strings[0]);
        this.result = del.result;
        return this.result;

    }

    @Override
    protected void onPostExecute(String s)
    {

    }


}
