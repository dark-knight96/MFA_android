package com.example.mfa_login;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.security.*;
import javax.crypto.*;
import android.Manifest;
import android.hardware.fingerprint.FingerprintManager;
import android.app.KeyguardManager;
import android.widget.Toast;
import android.security.keystore.*;
import com.example.mfa_login.Fingerprinthandler;
import com.multidots.fingerprintauth.FingerPrintAuthCallback;
import com.multidots.fingerprintauth.FingerPrintAuthHelper;

import android.security.keystore.KeyPermanentlyInvalidatedException;
import java.security.KeyStoreException;

public class Fingerprint_MFA extends AppCompatActivity implements FingerPrintAuthCallback{

    private FingerPrintAuthHelper mFingerPrintAuthHelper;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint__mf);

        mFingerPrintAuthHelper = FingerPrintAuthHelper.getHelper(this, this);

        //get keygaurd and fingerprint manager
    }

    @Override
    protected void onResume() {
        super.onResume();
        //start finger print authentication
        mFingerPrintAuthHelper.startAuth();
    }

    @Override
    public void onNoFingerPrintHardwareFound() {
        Toast.makeText(this, "No Fingerprint sensor found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNoFingerPrintRegistered() {
        Toast.makeText(getApplicationContext(),"Register atleast one fingerprint",Toast.LENGTH_LONG);
    }

    @Override
    public void onBelowMarshmallow() {
        Toast.makeText(this, "Android version does not support fingerprint authentication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthSuccess(FingerprintManager.CryptoObject cryptoObject) {
        Toast.makeText(this, "Authentication Successfull", Toast.LENGTH_SHORT).show();
        i = new Intent(Fingerprint_MFA.this,Code_activity.class);
        startActivity(i);
    }

    @Override
    public void onAuthFailed(int errorCode, String errorMessage) {
        Toast.makeText(this, "You are not allowed to access the resource", Toast.LENGTH_SHORT).show();
        Runtime.getRuntime().exit(0);
    }
}



