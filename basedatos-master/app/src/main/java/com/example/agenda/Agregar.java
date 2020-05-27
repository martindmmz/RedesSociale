package com.example.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Agregar extends AppCompatActivity {

    private EditText txt_nombre;
    private EditText txt_telefono;
    private EditText txt_correo;
    private EditText txt_fecha;
    private Button btn_guardar;
    Contacto UnContacto;
    DAOContactos dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        txt_nombre = (EditText)findViewById(R.id.txt_nombre);
        txt_telefono = (EditText)findViewById(R.id.txt_telefono);
        txt_correo = (EditText)findViewById(R.id.txt_correo);
        txt_fecha = (EditText)findViewById(R.id.txt_fecha);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        dao = new DAOContactos(this);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txt_nombre.getText().toString();
                String correo = txt_correo.getText().toString();
                String telefono = txt_telefono.getText().toString();
                String fecha = txt_fecha.getText().toString();

                dao.insert(new Contacto(0, nombre, correo, telefono, fecha));
                Intent principal = new Intent(Agregar.this, MainActivity.class);
                startActivity(principal);

            }
        });





    }



}
