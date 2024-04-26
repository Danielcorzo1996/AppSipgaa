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
    IHelpers helpers;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        helpers = new Helpers();

        auth = FirebaseAuth.getInstance();
        usuario = findViewById(R.id.usuarioRegistro);
        clave = findViewById(R.id.claveRegistro);
        correo = findViewById(R.id.correoRegistro);
    }

    public void registrarUsuario(View view){

        String usu = usuario.getText().toString();
        String cla = clave.getText().toString();
        String cor = correo.getText().toString();
        String error;

        try {
            error = helpers.checkFieldsOk(usu, cla, cor);
            //ASÍ EXISTE UN ERROR NO CONTINUA
            if(!error.isEmpty()){
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
                return;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
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