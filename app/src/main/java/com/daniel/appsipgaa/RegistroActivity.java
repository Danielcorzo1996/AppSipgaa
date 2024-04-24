package com.daniel.appsipgaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    EditText usuario, clave, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        usuario = findViewById(R.id.usuarioRegistro);
        clave = findViewById(R.id.claveRegistro);
        correo = findViewById(R.id.correoRegistro);
    }

    public void registrarUsuario(View view){

        String usu = usuario.getText().toString();
        String cla = clave.getText().toString();
        String cor = correo.getText().toString();

        if(TextUtils.isEmpty(usu)){
            Toast.makeText(this, "Ingresa un Usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(cla)){
            Toast.makeText(this, "Ingrega una Clave", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(cor)){
            Toast.makeText(this, "Ingrega un Correo", Toast.LENGTH_SHORT).show();
            return;
        }

        if(cla.length() < 6){
            Toast.makeText(this, "Clave minimo 6 caracteres!", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    public void irALogin(View view){
        startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
    }

}