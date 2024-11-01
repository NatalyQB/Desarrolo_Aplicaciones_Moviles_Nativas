package com.example.tiendaonline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CarritoComprasActivity extends AppCompatActivity {

    private TextView tvMensaje;
    private Button btnComprar;
    private List<Producto> carrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_compras);

        tvMensaje = findViewById(R.id.tvMensaje);
        btnComprar = findViewById(R.id.btnComprar);

        carrito = new ArrayList<>();

        List<Producto> carritoRecuperado = (List<Producto>) getIntent().getSerializableExtra("carrito");
        if (carritoRecuperado != null) {
            carrito.addAll(carritoRecuperado);
        }

        mostrarProductos();

        btnComprar.setOnClickListener(v -> {
            if (carrito.isEmpty()) {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show();
            } else {

                realizarCompra();
            }
        });
    }

    private void mostrarProductos() {
        if (carrito.isEmpty()) {
            tvMensaje.setText("El carrito está vacío.");
            btnComprar.setEnabled(false);
            btnComprar.setBackgroundColor(getResources().getColor(R.color.colorGris));           return;
        }

        StringBuilder mensaje = new StringBuilder("Productos en el carrito:\n");
        for (Producto producto : carrito) {
            mensaje.append(producto.getNombre()).append(" - ").append(producto.getPrecio()).append("\n");
        }
        tvMensaje.setText(mensaje.toString());


        btnComprar.setEnabled(true);
        btnComprar.setBackgroundColor(getResources().getColor(R.color.colorVerde)); // Cambia a color verde
    }

    private void realizarCompra() {
        Toast.makeText(this, "Compra realizada con éxito", Toast.LENGTH_SHORT).show();
        carrito.clear();
        finish();
    }
}
