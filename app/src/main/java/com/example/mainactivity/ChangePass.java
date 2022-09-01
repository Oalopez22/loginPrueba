package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mainactivity.db.DbUsuario;
import com.example.mainactivity.entidades.Usuario;
import com.example.mainactivity.modelos.SPreference;

public class ChangePass extends AppCompatActivity {
    EditText txtPassword,txtConfirmPassword;
    Button btnChangePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        txtPassword = findViewById(R.id.txtEditPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPass);
        btnChangePass = findViewById(R.id.btnChangePass);


        btnChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validar_pass();
            }
        });

    }

    private void validar_pass(){
        if (txtPassword.getText().toString().equals("")){
            Toast.makeText(this, "El campo contraseña no debe quedar vacio", Toast.LENGTH_SHORT).show();
        }
    }
}
