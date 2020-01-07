package com.hibbah.hibbahlib;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

public class HibbahUtils {
    private static final String TAG = "HibbahUtils";

    public static String getHibbah() {
        return "Hibbah";
    }

    public interface OnGoogleAdIdListener {
        void onSuccess(AdvertisingIdClient.Info adInfo);
        void onFail();
    }

    public static void getGoogleAdvertisingId(final Context context, final OnGoogleAdIdListener listener) {
        new AsyncTask<Void, Void, AdvertisingIdClient.Info>() {
            @Override
            protected AdvertisingIdClient.Info doInBackground(Void... params) {
                AdvertisingIdClient.Info adInfo = null;
                try {
                    adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.e(TAG, "getGoogleAdvertisingId() | GooglePlayServicesNotAvailableException", e);
                } catch (GooglePlayServicesRepairableException e) {
                    Log.e(TAG, "getGoogleAdvertisingId() | GooglePlayServicesRepairableException.", e);
                } catch (IOException e) {
                    Log.e(TAG, "getGoogleAdvertisingId() | IOException.", e);
                }
                return adInfo;
            }

            @Override
            protected void onPostExecute(AdvertisingIdClient.Info adInfo) {
                if (adInfo != null) {
                    listener.onSuccess(adInfo);
                } else {
                    listener.onFail();
                }
            }
        }.execute();
    }

}
