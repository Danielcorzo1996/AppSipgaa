package com.daniel.appsipgaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SeguimientoDeCompra extends AppCompatActivity   {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seguimiento_de_compra);


        ImageView imageView = findViewById(R.id.imageView3);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ubicación que se abrirá en Google Maps (latitud y longitud)
                String latitude = "4.653398142541321";
                String longitude = "-74.14554687480414";
                //4.653398142541321, -74.14554687480414

                // Crea un Uri que contiene la ubicación
                Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude);

                // Crea un Intent con la acción VIEW y el Uri de Google Maps
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

                // Define el tipo MIME del Intent
                mapIntent.setPackage("com.google.android.apps.maps");

                // Comprueba si hay aplicaciones disponibles para manejar el Intent
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    // Inicia el Intent
                    startActivity(mapIntent);
                }
            }
        });
    }

}