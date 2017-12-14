package com.example.mfa_login;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;

import java.util.concurrent.CancellationException;

/**
 * Created by prashanth on 12/12/17.
 */

public class Fingerprinthandler extends FingerprintManager.AuthenticationCallback {


    private CancellationSignal cancellationSignal;
    private Context context;
    public String result;

    public Fingerprinthandler(Context mcontext) {
        context = mcontext;

    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject crypt)
    {
        cancellationSignal  = new CancellationSignal();
        if(ActivityCompat.checkSelfPermission(context,Manifest.permission.USE_FINGERPRINT)!= PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        manager.authenticate(crypt,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
            this.result="AuthError";
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.result ="Authorized";
    }

    @Override
    public void onAuthenticationFailed() {
        this.result="You are not allowed to access this resource";
    }
}
