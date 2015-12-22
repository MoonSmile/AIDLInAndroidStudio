package com.test.huangxingli.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MAIDLServerService extends Service {



    public MAIDLServerService() {
    }

    AIDLServerService.Stub binder=new AIDLServerService.Stub() {


        @Override
        public String sayHello() throws RemoteException {
            return "hello, i am from AIDLServerService";
        }

        @Override
        public Girl getGirl() throws RemoteException {
            Girl girl=new Girl();
            girl.setAge(25);
            girl.setName("lily");
            return girl;
        }
    };


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       return binder;
    }






}
