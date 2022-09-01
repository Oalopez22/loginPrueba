package com.example.mainactivity.modelos;

import android.content.Context;
import android.content.SharedPreferences;

public class SPreference {

        Context context;
        SharedPreferences datos;
        SharedPreferences.Editor editor;

        public SPreference(Context context) {
            this.context = context;
            datos = context.getSharedPreferences("base_sp", Context.MODE_PRIVATE);
            editor = datos.edit();
        }

        public void setCorreo(String correo) {
            editor.putString("correo", correo);
            editor.apply();
        }

        public String getCorreo() {
            return datos.getString("correo", "Dato no encontrado");
        }
}
