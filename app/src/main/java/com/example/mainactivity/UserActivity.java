package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mainactivity.modelos.SPreference;

public class UserActivity extends AppCompatActivity {
    SPreference spreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
         spreference = new SPreference(UserActivity.this);


 /*       if (spreference.getCorreo() == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, UserActivity.class);
            startActivity(intent);
        }*/

    }


    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflow,menu);
        return  true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.menu_actualizar_datos){
            actualizarDatos();
            return  true;
        }else if (id == R.id.menu_ChangePass){
            actualizarPass();
            return true;
        }
        else if(id == R.id.menu_closeSession){
            Toast.makeText(this, "Boton cerrar sesion Seleccionado", Toast.LENGTH_LONG).show();
        }
        return  super.onOptionsItemSelected(item);
    }


    private  void actualizarDatos(){
        Intent intent = new Intent(this,EditActivity.class);
        startActivity(intent);
    }
    private  void actualizarPass(){
        Intent intent = new Intent(this,ChangePass.class);
        startActivity(intent);
    }

}