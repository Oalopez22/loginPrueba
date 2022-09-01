package com.example.mainactivity.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.mainactivity.entidades.Usuario;
import com.example.mainactivity.modelos.SPreference;

import java.util.ArrayList;

public class DbUsuario extends DbHelper{
    Button btnEditar;
    Context context;
    SPreference spreference;
    Usuario user;
    Integer id;
    public DbUsuario(@Nullable Context context) {
        super(context);
        this.context = context;

    }
    public long crearUsuario(String nombre, String correo, String password){
        long id = 0;
        try {
            DbHelper dbhelper = new DbHelper(context);
            SQLiteDatabase db = dbhelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("correo", correo);
            values.put("password", password);
            id = db.insert(TABLA_NOMBRES, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
}

    public boolean  revisarDatos(String correo, String password){
        SQLiteDatabase db = getWritableDatabase();
/*        Toast.makeText(context, "Correo :"+spreference.getCorreo()+" contraseña : "+spreference, Toast.LENGTH_SHORT).show();*/
            Cursor cursor = db.rawQuery("SELECT * FROM usuario WHERE correo=? AND password=?", new String[]{correo,password});
            if(cursor.getCount()>0){
                return  true;
            }else{
                return  false;
        }
    }


    public Usuario mostrarDatos(SPreference spreference){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursorUsuarios = null;
        user = new Usuario();
        cursorUsuarios = db.rawQuery("SELECT *  FROM "+ TABLA_NOMBRES+" WHERE correo "+"= '"+spreference.getCorreo()+"'",null);

        if(cursorUsuarios.moveToFirst()){
            user.setId(cursorUsuarios.getInt(0));
            user.setNombre(cursorUsuarios.getString(1));
            user.setCorreo(cursorUsuarios.getString(2));
            }
        cursorUsuarios.close();
        return user;
        }

        public boolean editarDatos(int id, String nombre, String correo){
            boolean correcto = true;
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            try {
                db.execSQL("UPDATE " + TABLA_NOMBRES + " SET nombre = '" + nombre + "', correo = '" + correo + "' WHERE _id='" + id + "' ");
                mostrarDatos(spreference);
                correcto = true;
            }catch (Exception ex){
                ex.toString();
                correcto = false;
            }finally {
                db.close();
            }
            return correcto;
        }
    public boolean editarPass(String password) {
        boolean changePass = true;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLA_NOMBRES + " SET password = " + password + " WHERE correo " + "= '" + spreference.getCorreo() + "'");
            changePass = true;
        } catch (Exception ex) {
            ex.toString();
            changePass = false;
        } finally {
            db.close();
        }
        return changePass;
    }
}
