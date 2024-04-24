package com.daniel.appsipgaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText usuario, clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.usuarioLogin);
        clave = findViewById(R.id.claveLogin);

    }

    public void loginSistema(View view) {

        String usu = usuario.getText().toString();
        String cla = clave.getText().toString();

        if(TextUtils.isEmpty(usu)){
            Toast.makeText(this, "Ingresa un Usuario", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(cla)){
            Toast.makeText(this, "Ingrega una Clave", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void olvideContra(View view) {
        startActivity(new Intent(LoginActivity.this, RestablecerClaveMainActivity.class));
    }



    public void registrarUsu(View view) {
        startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
    }

}