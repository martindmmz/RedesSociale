package net.ivanvega.mibasedatosp77b;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.text.method.SingleLineTransformationMethod;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DAOContacto dao =
                new DAOContacto(this);
        dao.insert(
                new Contacto(
                        0,"perronegro",
                        "perronegro@canes.com",
                        "445"
                )
        );
        dao.insert(
                new Contacto(
                        0,"perroblanco",
                        "perroblanco@canes.com",
                        "544"
                )
        );

        for (Contacto c : dao.getAll()){
            Toast.makeText(this,
                    c.getUsuario(),
                    Toast.LENGTH_SHORT).show();
        }

        Cursor c = dao.getAllCursor();


        lv = findViewById(R.id.lv);

        SimpleCursorAdapter adp = new SimpleCursorAdapter
                (this, android.R.layout.simple_list_item_2,
                        c,
                        new String[]{"usuario", "email"},
                        new int[]{android.R.id.text1,
                                android.R.id.text2},
                        SimpleCursorAdapter.IGNORE_ITEM_VIEW_TYPE);

        lv.setAdapter(adp);


    }
}
