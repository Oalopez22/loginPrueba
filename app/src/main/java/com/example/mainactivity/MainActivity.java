package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mainactivity.db.DbHelper;
import com.example.mainactivity.db.DbUsuario;
import com.example.mainactivity.entidades.Usuario;
import com.example.mainactivity.modelos.SPreference;

public class MainActivity extends AppCompatActivity {
    EditText txtcorreo, txtpassword;
    Button btnRegistrarse;
    Button btnIngresar;
    SharedPreferences sp;
    SPreference sPreference;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sPreference = new SPreference(this);
        /*usuario = new Usuario(this);*/



        DbHelper dbHelper = new DbHelper(MainActivity.this);
        dbHelper.getWritableDatabase();

        DbUsuario dbusuario = new DbUsuario(MainActivity.this);




        txtcorreo = findViewById(R.id.txtCorreo);
        txtpassword = findViewById(R.id.txtPassword);

        btnIngresar = findViewById(R.id.btnLogin);

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String correo = txtcorreo.getText().toString();

                String password = txtpassword.getText().toString();

                if (correo.equals("") || password.equals("")) {
                    Toast.makeText(MainActivity.this, "Campos correo y contraseña obligatorios", Toast.LENGTH_SHORT).show();
                } else {

                    Boolean revisarDatos = dbusuario.revisarDatos(correo,password);
                    if (revisarDatos) {
                        ingresarUsuario();
                        sPreference.setCorreo(correo);
                    }else{
                        Toast.makeText(MainActivity.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        });

        btnRegistrarse = findViewById(R.id.btnRegistrar);

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NuevoActivity.class);
                startActivity(intent);
            }
        });

    }
    private  void ingresarUsuario(){
        Intent intent = new Intent(this,UserActivity.class);
        startActivity(intent);
    }



}

