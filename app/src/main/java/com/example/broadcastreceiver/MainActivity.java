package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView mTvShowMsg;
    private Button mBtnSend;
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("rkp.sx7");
                intent.putExtra("name","Masai School");
                localBroadcastManager.sendBroadcast(intent);

            }
        });

        registerLocalReceiver();
    }

    private void registerLocalReceiver() {
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter("rkp.sx7");
        localBroadcastManager.registerReceiver(localReceiver,intentFilter);
    }

    private void initViews() {
        mTvShowMsg = findViewById(R.id.tvShowMsg);
        mBtnSend = findViewById(R.id.btnSend);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(localReceiver);
    }

    private class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                String data = intent.getStringExtra("name");
                mTvShowMsg.setText(data);
            }
        }
    }
}