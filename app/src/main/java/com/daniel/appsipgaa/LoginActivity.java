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

public class LoginActivity extends AppCompatActivity {

    EditText correo, clave;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        correo = findViewById(R.id.correoLogin);
        clave = findViewById(R.id.claveLogin);

    }

    public void loginSistema(View view) {

        String cor = correo.getText().toString();
        String cla = clave.getText().toString();

        if(TextUtils.isEmpty(cor)){
            Toast.makeText(this, "Ingresa un Correo", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(cla)){
            Toast.makeText(this, "Ingresa una Clave", Toast.LENGTH_SHORT).show();

        }

        auth.signInWithEmailAndPassword(cor, cla)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Bienvenido",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MenuActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Error de Ingreso"+task.getException(),Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    public void olvideContra(View view) {
        startActivity(new Intent(LoginActivity.this, RestablecerClaveMainActivity.class));
    }

    public void registrarUsu(View view) {
        startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
    }

}
