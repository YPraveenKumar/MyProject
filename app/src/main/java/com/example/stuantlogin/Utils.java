package com.example.stuantlogin;

import android.app.ProgressDialog;
import android.content.Context;

public class Utils {

    private static Utils mUtils;
    private ProgressDialog progressDialog;

    private Utils(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading..Please Wait..");
        progressDialog.setCancelable(false);
    }

    public static Utils getInstance(Context context) {
        if (mUtils == null) {
            mUtils = new Utils(context);
        }
        return mUtils;

    }

    public void showProgress() {
        progressDialog.show();

    }

    public void hideProgress() {
        progressDialog.hide();
    }
}
