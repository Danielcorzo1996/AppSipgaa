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

public class CarritoAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    private List<Producto> productosCarrito;
    Button btnRemoveItem;

    private OnProductosCarritoChangedListener listener;

    public CarritoAdapter(Context ctx, List<Producto> productos){
        this.context = ctx;
        inflater = LayoutInflater.from(ctx);
        this.productosCarrito = productos;
    }

    @Override
    public int getCount() {
        return productosCarrito.size();
    }

    @Override
    public Object getItem(int i) {
        return productosCarrito.get(i);
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

        view = inflater.inflate(R.layout.activity_carrito_fragment, null);
        TextView txtView = view.findViewById(R.id.textView);
        TextView textViewValorU = view.findViewById(R.id.textViewValorU);
        ImageView imageView = view.findViewById(R.id.imageIcon);
        btnRemoveItem = view.findViewById(R.id.btnRemove);

        Picasso.get().load(productosCarrito.get(i).getUrlImage()).into(imageView);

        Drawable icon = view.getResources().getDrawable(R.drawable.baseline_add_shopping_cart_24);
        icon.setColorFilter(view.getResources().getColor(R.color.green), PorterDuff.Mode.SRC_IN);
        RatingBar stars = view.findViewById(R.id.ratingBarProduct);

        try {
            txtView.setText(productosCarrito.get(i).getNombre());
            String textValorUnitario = String.valueOf(productosCarrito.get(i).getValor());
            textViewValorU.setText(textValorUnitario);
        } catch (Exception ex) {
            System.out.println("ERROR ->" + ex.getMessage());
        }

        stars.setRating(randomNumber);

        btnRemoveItem.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int i = 0;
                for(Producto item : productosCarrito){
                    if( item.getNombre().contentEquals( txtView.getText() ) ){
                        productosCarrito.remove(i);
                        updateProductosCarrito(productosCarrito);
                        break;
                    }
                    i++;
                }
            }
        });

        return view;
    }

    public List<Producto> getProductosCarrito() {
        return productosCarrito;
    }

    private void updateProductosCarrito(List<Producto> newListProducts) {
        this.productosCarrito = newListProducts;
        if (listener != null) {
            listener.onProductosCarritoChanged();
        }
    }

    public void removeOnProductosCarritoChangedListener(OnProductosCarritoChangedListener listener) {
        this.listener = listener;
    }

    public interface OnProductosCarritoChangedListener {
        void onProductosCarritoChanged();
    }
}
