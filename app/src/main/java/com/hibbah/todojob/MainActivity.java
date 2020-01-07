package com.hibbah.todojob;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.hibbah.hibbahlib.HibbahUtils;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        TextView txtHibbah = findViewById(R.id.txt_hibbah);
        txtHibbah.setText(HibbahUtils.getHibbah());

        final TextView txtAdId = findViewById(R.id.txt_adid);
        HibbahUtils.getGoogleAdvertisingId(this, new HibbahUtils.OnGoogleAdIdListener() {
            @Override
            public void onSuccess(AdvertisingIdClient.Info adInfo) {
                txtAdId.setText(adInfo.getId());
            }

            @Override
            public void onFail() {
                txtAdId.setText("NONE");
            }
        });
    }

}
