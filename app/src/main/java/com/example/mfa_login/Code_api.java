package com.example.mfa_login;

import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

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

public class Code_api {

    private String status;
    private OkHttpClient code_api = new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS).build();

    String url = "http://ije47jcb.burrow.io/add_code_api/";

    public Code_api(String Username,String code_recieved) {
        try {
            url =url +Username+"/"+code_recieved;
            MediaType JSON = MediaType.parse("application/json; charset=UTF-8");
            RequestBody body = RequestBody.create(JSON, "hello");
            Request req = new Request.Builder().post(body).url(url).build();
            Response res = code_api.newCall(req).execute();
            String stat = res.body().string();
            JSONObject json = new JSONObject(stat);
            this.status = stat;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getStat(){
        return status;

    }
}


