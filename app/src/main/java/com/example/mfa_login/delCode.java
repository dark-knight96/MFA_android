package com.example.mfa_login;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by prashanth on 11/12/17.
 */

public class delCode {
    protected String result;
    private OkHttpClient httpdel = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS).build();
    String URL = "http://ije47jcb.burrow.io/delcode/";

    public delCode(String username)
    {

        try
        {
            URL = URL+username;
            MediaType JSON  = MediaType.parse("application/json; charset=UTF-8");
            RequestBody body = RequestBody.create(JSON,"hello");
            Request req = new Request.Builder().post(body).url(URL).build();
            Response res = httpdel.newCall(req).execute();
            String details = res.body().string();
            JSONObject json = new JSONObject(details);
            this.result = json.getString("result");

        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
