package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mainactivity.db.DbUsuario;
import com.example.mainactivity.entidades.Usuario;
import com.example.mainactivity.modelos.SPreference;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class EditActivity extends AppCompatActivity {

    EditText txtnombre,txtcorreo;
    Button btnActualizar;
    SPreference sPreference;
    Usuario usuario;
    DbUsuario dbuser;
    Boolean correcto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        sPreference = new SPreference(EditActivity.this);
        dbuser = new DbUsuario(EditActivity.this);
        usuario = dbuser.mostrarDatos(sPreference);

        txtnombre = findViewById(R.id.txtEditNombre);
        txtcorreo = findViewById(R.id.txtEditCorreo);
        btnActualizar = findViewById(R.id.btn_actualizar);
        Toast.makeText(this, "Nombre"+usuario.getNombre(), Toast.LENGTH_SHORT).show();
        txtnombre.setText(usuario.getNombre());
        txtcorreo.setText(usuario.getCorreo());


        final DbUsuario dbuser = new DbUsuario(EditActivity.this);
        usuario = dbuser.mostrarDatos(sPreference);
        if(usuario != null) {
            txtnombre.setText(usuario.getNombre());
            txtcorreo.setText(usuario.getCorreo());

        }
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sPreference.getCorreo();

                if ( validarNombre(txtnombre)  && validarcorreo(txtcorreo) ){
                    correcto = dbuser.editarDatos(usuario.getId(),txtnombre.getText().toString(),txtcorreo.getText().toString());
                    if (correcto){
                        Toast.makeText(EditActivity.this, "Registro actualizado", Toast.LENGTH_LONG).show();
                        vista();
                    }else {
                        Toast.makeText(EditActivity.this, "Error al editar registro", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        /*
        Toast.makeText(this, "Bienvenido" +sPreference.getCorreo(), Toast.LENGTH_SHORT).show();*/

    }


           private boolean validarNombre(EditText txtnombre){
               String nombre = txtnombre.getText().toString();
               if(nombre.isEmpty()){
                   Toast.makeText(this, "El campo nombre no debe quedar vacio", Toast.LENGTH_LONG).show();
                   return false;
               }else{
                   return  true;
               }
           }

    private boolean validarcorreo(EditText txtcorreo){
        String correo = txtcorreo.getText().toString();
        if(correo.equals("")){
            Toast.makeText(this, "El campo correo no debe quedar vacio", Toast.LENGTH_LONG).show();
            return false;
        }else{
            return  true;
        }
    }

        private void vista(){
            Intent intent = new Intent(this, UserActivity.class);
            intent.getStringExtra(sPreference.getCorreo());
            startActivity(intent);
        }
}