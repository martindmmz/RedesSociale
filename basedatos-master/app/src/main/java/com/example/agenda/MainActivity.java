package com.example.agenda;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.app.SearchManager;
import androidx.appcompat.widget.SearchView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  SearchView.OnQueryTextListener{

    public static final int REQUEST_CODE = 10;
    private Button btn_agregar;
    private ListView lv_contactos;
    private ArrayAdapter<Contacto> adaptador;
    int usuarioseleccionado = -1;
    private Object mActionMode;
    private SearchView busqueda;
    DAOContactos dao;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        busqueda =
                (SearchView) menu.findItem(R.id.buscador).getActionView();
        busqueda.setOnQueryTextListener(this);






        return true;
    }


    public void OnCLick() {

                lv_contactos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                lv_contactos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                usuarioseleccionado = position;
                mActionMode = MainActivity.this.startActionMode(amc);
                view.setSelected(true);

                return true;
            }
        });
    }


   // private ActionMode.Callback amc = new ActionMode.Callback() {



    private ActionMode.Callback amc = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.opciones,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if(item.getItemId()==R.id.Eliminaritem){
                eliminarItem();
                mode.finish();

            }
            else if(item.getItemId()==R.id.Editaritem){
                editarItem();

            }

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        btn_agregar = (Button)findViewById(R.id.btn_agregar);
        dao = new DAOContactos(this);



        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ventana = new Intent(getApplicationContext(), Agregar.class
                );
                startActivity(ventana);

            }
        });






        lv_contactos = (ListView)findViewById(R.id.lv_contactos);
        adaptador = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1,  dao.getAll());
        lv_contactos.setAdapter(adaptador);
        registerForContextMenu(lv_contactos);
        OnCLick();





    }


    public void eliminarItem() {
        {

            DAOContactos dao = new DAOContactos(this);
            ArrayList<Contacto> datos = new ArrayList<>();
            datos = dao.getAll();

            int id = datos.get(usuarioseleccionado).getId();


            dao.borrar(id);
            lv_contactos = (ListView) findViewById(R.id.lv_contactos);
            adaptador = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1, dao.getAll());
            lv_contactos.setAdapter(adaptador);
            registerForContextMenu(lv_contactos);


        }



    }

    public void editarItem() {
        {

            DAOContactos dao = new DAOContactos(this);
            ArrayList<Contacto> datos = new ArrayList<>();
            datos = dao.getAll();

            Contacto seleccion = datos.get(usuarioseleccionado);
            String pasar = seleccion.getId()+","+seleccion.getUsuario()+","+seleccion.getTel()+","+seleccion.getEmail()+","+seleccion.getFecNac();
            Intent v = new Intent(getApplicationContext(), Editar.class);
            v.putExtra("recuperar",pasar);
            startActivity(v);



        }



    }



    public  void buscar(String s){

       try {

           adaptador = new ArrayAdapter<Contacto>(this, android.R.layout.simple_list_item_1,  dao.buscar(s));
           lv_contactos.setAdapter(adaptador);

       }catch (Exception e){


       }
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        buscar(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        buscar(s);


        return true;
    }
}

