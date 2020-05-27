package com.martin.modo_host;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        UsbDevice device = (UsbDevice) getIntent().getParcelableExtra(UsbManager.EXTRA_DEVICE);


        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();

        UsbDevice dispositivo = deviceList.get("deviceName");


        if(deviceList.isEmpty()){
            Toast.makeText(this,"No hay ninún dispositivo conectado",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(this,"Se han encontrado los siguientes dispositivos",Toast.LENGTH_LONG).show();

            for(int i=0;i<deviceList.size();i++){
                Toast.makeText(this,"Dispositivo numero "+i+": "+deviceList.get(i).toString(),Toast.LENGTH_LONG).show();

            }

        }


    }
}
