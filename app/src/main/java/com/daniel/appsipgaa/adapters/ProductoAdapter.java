package com.daniel.appsipgaa.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daniel.appsipgaa.R;
import com.daniel.appsipgaa.models.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductoAdapter extends BaseAdapter {

    Context context;
    String[] listFruit;
    LayoutInflater inflater;

    List<Producto> productos;
    private List<Producto> productosCarrito;
    Button btnAddItems;
    private int sizeItems = 0;
    private OnProductosCarritoChangedListener listener;

    public ProductoAdapter(Context ctx, String[] fruitList, List<Producto> productos){
        this.context = ctx;
        this.listFruit = fruitList;
        inflater = LayoutInflater.from(ctx);
        this.productos = productos;

        productosCarrito = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int i) {
        return productos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // Create a Random object
        Random random = new Random();

        // Generate a random number between 1 and 5
        int randomNumber = random.nextInt(5) + 1;

        view = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView txtView = view.findViewById(R.id.textView);
        TextView textViewValor = view.findViewById(R.id.textViewValor);
        ImageView imageView = view.findViewById(R.id.imageIcon);
        btnAddItems = view.findViewById(R.id.button);

        Button buttonAdd = view.findViewById(R.id.button);
        Drawable icon = view.getResources().getDrawable(R.drawable.baseline_add_shopping_cart_24);
        icon.setColorFilter(view.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
        RatingBar stars = view.findViewById(R.id.ratingBar);
        EditText  editTextTotalByProduct = view.findViewById(R.id.editTextNumberDecimal);
        TextView textViewName = view.findViewById(R.id.textView);

        //txtView.setText(listFruit[i]);
        try{
            txtView.setText(productos.get(i).getNombre());
            textViewValor.setText("$ " + productos.get(i).getValor());
        }catch (Exception ex){
            System.out.println("ERROR ->" + ex.getMessage());
        }

        Picasso.get().load(productos.get(i).getUrlImage()).into(imageView);
        buttonAdd.setCompoundDrawablesWithIntrinsicBounds(icon, null, null, null);
        stars.setRating(randomNumber);


        btnAddItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int totalBYProduct = 0;

                try{
                    String txt = editTextTotalByProduct.getText().toString();
                    totalBYProduct = Integer.parseInt(txt);
                }catch (Exception ex){
                    System.out.println("error -> " + ex.getMessage());
                }

                if(totalBYProduct > 0){
                    for(Producto item : productos){
                        if( item.getNombre().contentEquals( textViewName.getText() ) ){
                            for(int i = 0; i < totalBYProduct; i++){
                                productosCarrito.add(item);
                            };

                            updateProductosCarrito(productosCarrito);
                            break;
                        }
                    }
                }
            }
        });

        return view;
    }


    public List<Producto> getProductosCarrito() {
        return productosCarrito;
    }


    // Method to update productosCarrito and notify the listener
    private void updateProductosCarrito(List<Producto> updatedProductos) {
        this.productosCarrito = updatedProductos;
        if (listener != null) {
            listener.onProductosCarritoChanged();
        }
    }

    public void setOnProductosCarritoChangedListener(OnProductosCarritoChangedListener listener) {
        this.listener = listener;
    }

    public interface OnProductosCarritoChangedListener {
        void onProductosCarritoChanged();
    }
}
