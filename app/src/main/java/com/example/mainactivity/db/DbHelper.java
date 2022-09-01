package com.example.mainactivity.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "login.db";

    public static final String TABLA_NOMBRES = "usuario";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_CORREO = "correo";
    public static final String COLUMNA_PASSWORD = "password";


    private static final String SQL_CREAR = "create table "
            + TABLA_NOMBRES + "(" + COLUMNA_ID
            + " integer primary key autoincrement, " + COLUMNA_NOMBRE
            + " text not null, " + COLUMNA_CORREO
            + " text not null, " + COLUMNA_PASSWORD
            + " text not null);";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREAR);
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
