package com.daniel.appsipgaa;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.daniel.appsipgaa.adapters.CarritoAdapter;
import com.daniel.appsipgaa.adapters.ProductoAdapter;
import com.daniel.appsipgaa.globalInfo.User;
import com.daniel.appsipgaa.models.DetalleVenta;
import com.daniel.appsipgaa.models.Producto;
import com.daniel.appsipgaa.models.Venta;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Carrito extends AppCompatActivity {

    FirebaseFirestore db;
    List<Producto> productos;
    ListView listView;
    TextView textViewTotalValue;

    private int totalValue = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);


        // Get a reference to the Firebase database
        db = FirebaseFirestore.getInstance();

        // Initialize list of products
        Intent intent = getIntent();
        productos = (List<Producto>) intent.getSerializableExtra("productList");

        listView = findViewById(R.id.listViewCarrito);
        CarritoAdapter adapter = new CarritoAdapter(getApplicationContext(),  productos);
        adapter.removeOnProductosCarritoChangedListener(new CarritoAdapter.OnProductosCarritoChangedListener() {
            @Override
            public void onProductosCarritoChanged() {
                productos = adapter.getProductosCarrito();
                adapter.notifyDataSetChanged();
                setTotalValue();
            }
        });

        listView.setAdapter(adapter);

        textViewTotalValue = findViewById(R.id.textViewTotalValue);

        setTotalValue();
    }

    public void clickVolver(View view){
        finish();
    }


    public void clickSalir(View view){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        User user = User.getInstance();
        user.setEmail("");
    }

    private void setTotalValue(){
        totalValue = 0;
        for(Producto item : productos){
            totalValue += item.getValor();
        }

        textViewTotalValue.setText("$ " + String.valueOf(totalValue));
    }


    public void clickComprar(View view){
        String idVenta = UUID.randomUUID().toString();

        setVenta(idVenta);
        setDetalleVenta(idVenta);

        startActivity(new Intent(this, SeguimientoDeCompra.class));

    }

    private void setVenta(String idVenta){
        User user = User.getInstance();
        Venta venta = new Venta( idVenta, user.getEmail(), totalValue, Instant.now() );

        db.collection("ventas")
                .add(venta)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void setDetalleVenta(String idVenta){

        Map<String, Integer> cantidadPorProducto = new HashMap<>();

        for (Producto producto : productos) {
            cantidadPorProducto.put(producto.getId_producto(), cantidadPorProducto.getOrDefault(producto.getId_producto(), 0) + 1);
        }

        List<DetalleVenta> detallesVenta = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : cantidadPorProducto.entrySet()) {
            String key = entry.getKey();
            Integer unidades = entry.getValue();
            // Process key and value
            DetalleVenta detalleVenta = new DetalleVenta(idVenta);


            for(Producto producto : productos){
                if(Objects.equals(producto.getId_producto(), key)){
                    detalleVenta.setValorUnitario(producto.getValor());
                    detalleVenta.setUnidades(unidades);
                    detalleVenta.setValorTotal(producto.getValor() * unidades);
                    detalleVenta.setProductoId(key);
                    detalleVenta.setNombre(producto.getNombre());
                    break;
                }
            }

            detallesVenta.add(detalleVenta);
        }


        for(DetalleVenta item  : detallesVenta){
            db.collection("detalle_venta")
                    .add(item)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });
        }
    }
}