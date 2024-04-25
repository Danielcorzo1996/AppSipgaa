package com.daniel.appsipgaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RestablecerClaveMainActivity extends AppCompatActivity {
    EditText correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_clave_main);

        correo = findViewById(R.id.correoRestablecer);
    }

    public void restablecerContrasena(View view){

        String cor = correo.getText().toString();

        if(TextUtils.isEmpty(cor)){
            Toast.makeText(this, "Ingresa un Correo", Toast.LENGTH_SHORT).show();
            return;
        }
    }
    public void volverALogin(View view) {
        startActivity(new Intent(RestablecerClaveMainActivity.this, LoginActivity.class));
    }
}