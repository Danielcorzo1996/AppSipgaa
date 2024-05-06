package com.daniel.appsipgaa;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.daniel.appsipgaa.adapters.ProductoAdapter;
import com.daniel.appsipgaa.globalInfo.User;
import com.daniel.appsipgaa.models.Producto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tienda extends AppCompatActivity {

    String[] fruistList = {"Apple","Banana","Pineapple","Apple","Banana","Pineapple","Apple","Banana","Pineapple","Apple","Banana","Pineapple"};
    FirebaseFirestore db;
    List<Producto> productos;
    Button btnCarrito;
    Drawable iconCarrito;

    ListView listView;
    ProductoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        // Get a reference to the Firebase database
        db = FirebaseFirestore.getInstance();


        // Initialize list of products
        productos = new ArrayList<>();

        //AJUSTO LOS ESTILOS
        TextView textView = findViewById(R.id.textView_title);
        textView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

        listView = findViewById(R.id.listView);
        adapter = new ProductoAdapter(getApplicationContext(), fruistList, productos);
        adapter.setOnProductosCarritoChangedListener(new ProductoAdapter.OnProductosCarritoChangedListener() {
            @Override
            public void onProductosCarritoChanged() {
                System.out.println("itemsCARRITO -> " + adapter.getProductosCarrito().size());
                updateButtonText();
            }
        });

        listView.setAdapter(adapter);

        btnCarrito = findViewById(R.id.btnCarrito);
        btnCarrito.setBackgroundResource(R.drawable.button_border);

        iconCarrito = getResources().getDrawable(R.drawable.ic_baseline_shopping_cart_24);
        iconCarrito.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_IN);


        btnCarrito.setCompoundDrawablesWithIntrinsicBounds(iconCarrito, null, null, null);
        //btnCarrito.setText("Ver carrito " + adapter.getProductosCarrito().size());

        getProducts();
    }

    private void updateButtonText() {
        // Update button text with the size of ProductosCarrito
        System.out.println("itemsCARRITO -> " + adapter.getProductosCarrito().size());
        btnCarrito.setText("Ver carrito " + adapter.getProductosCarrito().size());
    }

    public void verCarrito(View view) {
        System.out.println("CLICKEADO - TOTAL -> " + adapter.getProductosCarrito().size());
        Intent intent = new Intent(this, Carrito.class);
        intent.putExtra("productList", (Serializable) adapter.getProductosCarrito());
        startActivity(intent);
    }

    private void getProducts(){

        db.collection("productos")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    Producto producto = new Producto(
                                            document.getString("descripcion"),
                                            document.getString("id_producto"),
                                            document.getString("nombre"),
                                            document.getDouble("stock").intValue(),
                                            document.getDouble("valor").intValue(),
                                            document.getString("url_image")
                                    );
                                    productos.add(producto);
                                    //Log.d(TAG, document.getId() + " => " + document.getData());
                                }
                                adapter.notifyDataSetChanged();

                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                            }
                        }
                    });

    }

    public void setProducto(){

        Producto producto = new Producto(
                "camara",
                "wemoek98we",
                "esto es una camara",
                10,
                350000,
                ""
                    );


        // Add a new document with a generated ID
        db.collection("productos")
                .add(producto)
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


    public void clickSalir(View view){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        User user = User.getInstance();
        user.setEmail("");
    }
}