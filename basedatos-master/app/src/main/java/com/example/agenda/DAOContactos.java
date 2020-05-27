package com.example.agenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class DAOContactos {
    SQLiteDatabase _sqLiteDatabase;
    Context ctx;

    public DAOContactos(Context ctx) {
        this.ctx = ctx;
        _sqLiteDatabase =
                new MiDB(ctx).getWritableDatabase();
    }

    public long insert(Contacto contacto) {


        ContentValues contentValues
                = new ContentValues();

        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[1],
                contacto.getUsuario());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[3],
                contacto.getEmail());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[2],
                contacto.getTel());
        contentValues.put(MiDB.COLUMNS_NAME_CONTACTO[4],
                contacto.getFecNac());


        return _sqLiteDatabase.insert(MiDB.TABLE_NAME_CONTACTOS,
                null, contentValues);

    }

    public ArrayList<Contacto> getAll() {
        ArrayList<Contacto> lst = null;

        Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_NAME_CONTACTO,
                null,
                null,
                null,
                null,
                null,
                null);

        if (c.moveToFirst()) {
            lst = new ArrayList<Contacto>();
            do {
                Contacto contacto =
                        new Contacto(c.getInt(0), c.getString(1),
                                c.getString(2), c.getString(3), c.getString(4));
                lst.add(contacto);

            } while (c.moveToNext());
        }
        return lst;

    }

    public Cursor getAllCursor() {


        Cursor c = _sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                MiDB.COLUMNS_NAME_CONTACTO,
                null,
                null,
                null,
                null,
                null,
                null);


        return c;

    }

    public ArrayList<Contacto> buscar(String name){
        ArrayList<Contacto> lst = null;


            Cursor c = _sqLiteDatabase.query("contactos", MiDB.COLUMNS_NAME_CONTACTO, "usuario" + " LIKE '%" + name + "%'",
                    null,
                    null,
                    null,
                    null);

            if (c.moveToFirst()) {
                lst = new ArrayList<Contacto>();
                do {
                    Contacto contacto =
                            new Contacto(c.getInt(0), c.getString(1),
                                    c.getString(2), c.getString(3), c.getString(4));
                    lst.add(contacto);

                } while (c.moveToNext());
            }

            return lst;


    }


    public void borrar(int id) {

        String consulta = "_id=" + id;
        _sqLiteDatabase.delete("Contactos", consulta, null);



    }


    public void  editar(Contacto contacto){

        int id = contacto.getId();
        ContentValues valores = new ContentValues();
        String update = "0,";

        //"_id", "usuario", "email", "tel", "fecNacimiento"
        valores.put("usuario",contacto.getUsuario());
        valores.put("email",contacto.getEmail());
        valores.put("tel",contacto.getTel());
        valores.put("fecNacimiento",contacto.getFecNac());


        _sqLiteDatabase.update(MiDB.TABLE_NAME_CONTACTOS, valores,"_id="+id,null);


    }



}
