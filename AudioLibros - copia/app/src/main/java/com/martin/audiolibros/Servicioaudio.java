package com.martin.audiolibros;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationBuilderWithBuilderAccessor;
import androidx.core.app.NotificationCompat;

import java.io.IOException;

public class Servicioaudio extends Service {
    MediaPlayer reproductor;
    String NOTIFICATION_ID = "audiolibros_canal";
    int INT_ID = 75450;

    public Servicioaudio() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {


        super.onCreate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        reproductor = new MediaPlayer();
        String path = intent.getExtras().getString("entrada");
        String titulo = intent.getExtras().getString("titulo");
        int imagen = intent.getExtras().getInt("imagen");


        Uri audio = Uri.parse(path);

        try {
            reproductor.setDataSource(path);
            reproductor.prepare();
            reproductor.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        CharSequence nombre = "audiolibros";
        NotificationChannel canal =
                new NotificationChannel
                        (NOTIFICATION_ID,nombre, NotificationManager.IMPORTANCE_HIGH);




        NotificationCompat.Builder construir;
        construir = new NotificationCompat.Builder(this,null);
        construir.setChannelId(NOTIFICATION_ID);




        Bitmap icon = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.matrimonio_sabuesos);


        construir.setContentTitle("Estás escuchando: "+titulo);
        construir.setSmallIcon(R.drawable.books);
        construir.addAction(R.drawable.icono_play,null,null);
        construir.addAction(R.drawable.icono_parar,null,null);





        NotificationManager notificationManager =
                (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(canal);

        startForeground(INT_ID,construir.build());



        return START_STICKY;
    }

    @Override
    public void onDestroy() {

     reproductor.stop();

    }


}
