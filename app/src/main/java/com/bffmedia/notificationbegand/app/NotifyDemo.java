package com.bffmedia.notificationbegand.app;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;


public class NotifyDemo extends Activity {
    private static final int NOTIFY_ME_ID = 1337;
    private Timer timer = new Timer();
    private int count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_demo);

        Button btn = (Button)findViewById(R.id.notify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimerTask task = new TimerTask(){
                    public void run(){
                        notifyMe();
                    }
                };

                timer.schedule(task, 5000);
            }
        });

        btn =(Button) findViewById(R.id.cancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                mgr.cancel(NOTIFY_ME_ID);
            }
        });


    }

    private void notifyMe() {
        /*
        final NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification note = new Notification(R.drawable.abc_ab_bottom_solid_dark_holo, "Status message!", System.currentTimeMillis());
        PendingIntent i = PendingIntent.getActivity(this, 0, new Intent(this, Notification.class), 0);
        note.setLatestEventInfo(this, "Notification Title", "This is the notification message", i);
        note.number=++count;
        mgr.notify(NOTIFY_ME_ID, note);
        */


        //this works on emulator 2.7inchQvga 3.7nexus and 4.0nexus but fails on myphone2.3.6
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Sets an ID for the notification, so it can be updated
        int notifyID = 1;
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setSmallIcon(R.drawable.abc_ab_share_pack_holo_dark);
        int numMessages = 0;
        // Start of a loop that processes data and then notifies the user
        mNotifyBuilder.setContentText("You've received new messages." + String.valueOf(++count));
        // Because the ID remains unchanged, the existing notification is
        // updated.
        mNotificationManager.notify(notifyID, mNotifyBuilder.build());


        /* this work on 2.7QVGAemulator
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Sets an ID for the notification, so it can be updated
        int notifyID = 1;
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setSmallIcon(R.drawable.abc_ab_share_pack_holo_dark);
        int numMessages = 0;
        // Start of a loop that processes data and then notifies the user
        mNotifyBuilder.setContentText("You've received new messages." + String.valueOf(++count));
        // Because the ID remains unchanged, the existing notification is
        // updated.
        //mNotificationManager.notify(notifyID, mNotifyBuilder.build());

        Notification notification = mNotifyBuilder.getNotification();
        mNotificationManager.notify(notifyID, notification);
        */
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.notify_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
