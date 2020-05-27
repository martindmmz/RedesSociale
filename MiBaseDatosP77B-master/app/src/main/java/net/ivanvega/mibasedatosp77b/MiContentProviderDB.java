package net.ivanvega.mibasedatosp77b;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MiContentProviderDB extends ContentProvider {
    // Creates a UriMatcher object.
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    SQLiteDatabase sqLiteDatabase;


    static {
        /*
         * The calls to addURI() go here, for all of the content URI patterns that the provider
         * should recognize. For this snippet, only the calls for table 3 are shown.
         */

        /*
         * Sets the integer value for multiple rows in table 3 to 1. Notice that no wildcard is used
         * in the path
         */
        uriMatcher.addURI("net.ivanvega.mibasedatosp77b.provider", "contactos", 1);

        /*
         * Sets the code for a single row to 2. In this case, the "#" wildcard is
         * used. "content://net.ivanvega.mibasedatosp77b.provider/contactos" matches, but
         * "content://com.example.app.provider/table3 doesn't.
         */
        uriMatcher.addURI("net.ivanvega.mibasedatosp77b.provider", "contactos/#", 2);

        uriMatcher.addURI("net.ivanvega.mibasedatosp77b.provider", "contactos/*", 3);
    }



    public MiContentProviderDB() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.

        String resul="";

        switch (uriMatcher.match(uri)){

            case 1:
                resul = "vnd.android.cursor.dir/vnd.net.ivanvega.mibasedatosp77b.provider.contactos";
                break;
            case 2 :
                resul = "vnd.android.cursor.item/vnd.net.ivanvega.mibasedatosp77b.provider.contactos";
                break;
        }

        return resul;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri resul=null;

        // TODO: Implement this to handle requests to insert a new row.
        switch (uriMatcher.match(uri)){
            case 1:

                  resul = Uri.withAppendedPath(uri,
                          String.valueOf(   sqLiteDatabase.insert(MiDB.TABLE_NAME_CONTACTOS,
                        null, values)));

                break;
        }

        return resul;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.


        sqLiteDatabase =
                new MiDB(this.getContext()).getWritableDatabase();

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {


        Cursor c = null;


        switch (uriMatcher.match(uri)){

            case 1:
                c = sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                        projection , null, null,
                        null, null, null );
                break;
            case 2 :
                c = sqLiteDatabase.query(MiDB.TABLE_NAME_CONTACTOS,
                        projection ,
            MiDB.COLUMNS_CONTACTOS[0] + "=?",
                        new String[]{uri.getLastPathSegment()},
                        null,null,null
                        );
                break;
        }


        // TODO: Implement this to handle query requests from clients.

        return  c;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {




        throw new UnsupportedOperationException("Not yet implemented");
    }
}
