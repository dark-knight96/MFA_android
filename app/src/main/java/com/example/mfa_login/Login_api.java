package com.example.mfa_login;


import android.util.Log;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;

/**
 * Created by prashanth on 9/12/17.
 */

public class Login_api {

    private OkHttpClient http = new OkHttpClient.Builder().connectTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS).build();

    String urlstring ="https://ije47jcb.burrow.io/login_api/";
    public String result;
    public Login_api(String name,String password)
    {
        urlstring = urlstring+name+"/"+password;

        try
        {
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON,"hello");
            Request req = new Request.Builder().url(urlstring).post(body).build();
            Response res = http.newCall(req).execute();
            String justinformation = res.body().string();
            JSONObject js = new JSONObject(justinformation);
            this.result = js.getString("result");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public String returnString()
    {
        return this.result;
    }

}
