package com.daniel.appsipgaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class RestablecerClaveMainActivity extends AppCompatActivity {
    EditText correo;
    Button botonRestablecerClave;
    String email = "";
    FirebaseAuth nAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_clave_main);
        nAuth = FirebaseAuth.getInstance();

        correo = findViewById(R.id.correoRestablecer);
        botonRestablecerClave = findViewById(R.id.botonRestablecerCorreo);

    }

    public void restablecerContrasena(View view){

        String cor = correo.getText().toString();

        if(TextUtils.isEmpty(cor)){
            Toast.makeText(this, "Ingresa un Correo", Toast.LENGTH_SHORT).show();
            return;
        }

        botonRestablecerClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email =  correo.getText().toString();
                    resetPassword();

            }
        });
    }

    public void resetPassword(){
        nAuth.setLanguageCode("es");
        nAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RestablecerClaveMainActivity.this, "Enviamos un formulario para restablecer tu Contraseña", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(RestablecerClaveMainActivity.this, "Correo no valido", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    public void volverALogin(View view) {
        startActivity(new Intent(RestablecerClaveMainActivity.this, LoginActivity.class));
    }
}