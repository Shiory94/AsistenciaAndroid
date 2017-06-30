package com.example.asus.proyectoasistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ASUS on 30/06/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "register.db";
    private static final String TABLE_NOMBRE = "register";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_APELLIDOS = "apellidos";
    private static final String COLUMN_USUARIO = "usuario";
    private static final String COLUMN_CONTRASEÑA = "contrasenia";
    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table register (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "nombre text not null, apellidos text not null, usuario text not null, contrasenia text not null);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertRegister(Register r) {

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from register";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();


        values.put(COLUMN_ID, count);
        values.put(COLUMN_NOMBRE, r.getNombre());
        values.put(COLUMN_APELLIDOS, r.getApellidos());
        values.put(COLUMN_USUARIO, r.getUsuario());
        values.put(COLUMN_CONTRASEÑA, r.getContraseña());


        db.insert(TABLE_NOMBRE, null, values);
        db.close();

    }

    public String searchContraseña(String u) {
        db = this.getReadableDatabase();
        String query = "select usuario, contrasenia from " + TABLE_NOMBRE;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";

        Log.i("####Login####", "Llegue");

        if (cursor.moveToFirst()) {
            do {
                Log.i("####user2####", cursor.getString(0));
                a = cursor.getString(0);
                if (a.equals(u)) {
                    b = cursor.getString(1);
                    break;
                }

            } while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String query = "DROP TABLE IF EXISTS " + TABLE_NOMBRE;
        db.execSQL(query);
        this.onCreate(db);
    }
}
