package com.daniel.appsipgaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RestablecerClaveMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_clave_main);

    }
    public void volverALogin(View view) {
        startActivity(new Intent(RestablecerClaveMainActivity.this, LoginActivity.class));
    }
}