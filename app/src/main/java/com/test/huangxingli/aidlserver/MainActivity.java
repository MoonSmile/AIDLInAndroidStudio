package com.test.huangxingli.aidlserver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView textView;
    Button button;
    AIDLServerService aidlServerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button= (Button) findViewById(R.id.button);
        textView= (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.test.huangxingli.aidlserver.MAIDLServerService");
                bindService(intent,connection,BIND_AUTO_CREATE);
            }
        });

    }

    ServiceConnection connection=new ServiceConnection() {

        String content;
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlServerService=AIDLServerService.Stub.asInterface(service);
            try {
                content=aidlServerService.sayHello()+"\n";
                Girl girl=aidlServerService.getGirl();
                content +="my name is "+girl.getName();

                textView.setText(content);

            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidlServerService=null;
        }
    };



}
