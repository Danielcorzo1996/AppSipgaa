package com.daniel.appsipgaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity {

    EditText usuario, clave, correo;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        auth = FirebaseAuth.getInstance();
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
            Toast.makeText(this, "Ingresa una Clave", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(cor)){
            Toast.makeText(this, "Ingresa un Correo", Toast.LENGTH_SHORT).show();
            return;
        }

        if(cla.length() < 6){
            Toast.makeText(this, "Clave minimo 6 caracteres!", Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(cor, cla)
                .addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegistroActivity.this, "Usuario registrado Con Exito",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                        }else{
                            Toast.makeText(RegistroActivity.this, "Error de Registro"+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    public void irALogin(View view){
        startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
    }

}