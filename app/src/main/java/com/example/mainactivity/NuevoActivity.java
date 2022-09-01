package com.example.mainactivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mainactivity.db.DbUsuario;

import java.util.regex.Pattern;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNuevoNombre, txtNuevoCorreo, txtNuevoPassword;
    Button btnRegistrar;
    private  static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
        txtNuevoNombre = findViewById(R.id.txtEditNombre);
        txtNuevoCorreo = findViewById(R.id.txtEditCorreo);
        txtNuevoPassword = findViewById(R.id.txtEditPassword);
        btnRegistrar = findViewById((R.id.btnRegistrarse));
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarNombre(txtNuevoNombre) && validarEmail(txtNuevoCorreo) && validarPass(txtNuevoPassword)){
                                    DbUsuario dbusuario = new DbUsuario(NuevoActivity.this);
                long id = dbusuario.crearUsuario(txtNuevoNombre.getText().toString(),txtNuevoCorreo.getText().toString(),txtNuevoPassword.getText().toString());

                    if(id > 0 ){
                        Toast.makeText(NuevoActivity.this, "Usuario creado", Toast.LENGTH_LONG).show();
                        limpiar();
                    }else{
                        Toast.makeText(NuevoActivity.this, "Error al crear el usuario", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(NuevoActivity.this, "Error al insertar registro", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private  boolean validarEmail(EditText txtNuevoCorreo){
        String email = txtNuevoCorreo.getText().toString();
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }else{
            txtNuevoCorreo.setError("E-mail oblogatorio");
            return false;
        }
    }

    private boolean validarNombre(EditText txtNuevoNombre){
        String nombre = txtNuevoNombre.getText().toString();
        if(!nombre.isEmpty()){
            return true;
        }else{
            txtNuevoNombre.setError("Campo obligatorio");
            return  false;
        }
    }

    private  boolean validarPass(EditText txtNuevoPassword){
        String passwordInput = txtNuevoPassword.getText().toString();
        if(!passwordInput.isEmpty()){
            return true;
        } else if(!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(NuevoActivity.this);
            builder.setMessage("La contraseña debe incluir al menos un número, una letra en mayúscula, una  en minúscula y no debe incluir espacios")
                    .setTitle("Error")
                    .setCancelable(false)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    }).show();
            return false;
        } else {
            txtNuevoPassword.setError("Campo obligatorio");
            return  false;
        }
    }

    private void limpiar(){
        txtNuevoNombre.setText("");
        txtNuevoCorreo.setText("");
        txtNuevoPassword.setText("");
    }


}