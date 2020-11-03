package com.example.testlib;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.base_common_lib.Arouter_path;
import com.example.base_common_lib.Utils.LogUtils;

public class MyForegroundService extends Service {
    private static final String TAG = MyForegroundService.class.getSimpleName();
    private static final String ID="channel_1";
    private static final String NAME="前台服务";

    private static final int REQUEST_CODE = 100;
    private static final String ACTION_PLAY = "123";
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException ("Not yet implemented");
    }

    @Override
    public void onCreate(){
        super.onCreate ();
        LogUtils.e("onCreate");

        if(Build.VERSION.SDK_INT>=26){
//            setForeground();
            setCustom();
        }else{

        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy ();
        Log.d (TAG,"onDestroy");
        stopForeground(true);
    }

    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        Log.d(TAG,"onStartCommand");
        return super.onStartCommand (intent,flags,startId);
    }

    @TargetApi(26)
    private void setForeground(){
        NotificationManager manager=(NotificationManager)getSystemService (NOTIFICATION_SERVICE);
        NotificationChannel channel=new NotificationChannel (ID,NAME,NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel (channel);
        Notification notification=new Notification.Builder (this,ID)
                .setContentTitle ("收到一条重要通知")
                .setContentText ("这是重要通知")
                .setSmallIcon (R.mipmap.ic_launcher)
                .setLargeIcon (BitmapFactory.decodeResource (getResources (),R.mipmap.ic_launcher))
                .build ();
        startForeground (1,notification);
    }

    @TargetApi(26)
    private void setCustom(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        RemoteViews remoteViews = new RemoteViews(this.getPackageName(),
                R.layout.notification_layout);// 获取remoteViews（参数一：包名；参数二：布局资源）
       Notification.Builder builder = new Notification.Builder(this.getApplicationContext(),ID)
                .setContent(remoteViews);// 设置自定义的Notification内容
        builder.setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher);


        Intent intentPlay = new Intent(this,GlideActivity.class);// 指定操作意图--设置对应的行为ACTION
        PendingIntent pIntentPlay = PendingIntent.getActivity(this.getApplicationContext(),
                REQUEST_CODE, intentPlay, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.view_left, pIntentPlay);

        Intent intentPlay02 = new Intent(this,Dialog_Activity.class);// 指定操作意图--设置对应的行为ACTION
        PendingIntent pintentPlay02 = PendingIntent.getActivity(this.getApplicationContext(),
                REQUEST_CODE, intentPlay02, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.view_right, pintentPlay02);

        Notification notification = builder.build();
        startForeground(1, notification);// 开始前台服务


    }

}
