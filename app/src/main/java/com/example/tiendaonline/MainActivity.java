package com.example.tiendaonline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnListadoProductos;
    private Button btnRegistro;
    private Button btnCarrito;
    private Button btnGeolocalizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initializeButtons();


        setupButtonListeners();
    }

    private void initializeButtons() {
        btnLogin = findViewById(R.id.btnLogin);
        btnListadoProductos = findViewById(R.id.btnListadoProductos);
        btnRegistro = findViewById(R.id.btnRegistroClientes);
        btnCarrito = findViewById(R.id.btnCarritoCompras);
        btnGeolocalizacion = findViewById(R.id.btnGeolocalizacion);
    }

    private void setupButtonListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity(LoginActivity.class);
            }
        });


        btnListadoProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity(ListadoProductosActivity.class);
            }
        });


        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity(TercerosActivity.class);
            }
        });


        btnCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToActivity(CarritoComprasActivity.class);
            }
        });
        btnGeolocalizacion.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GeolocalizacionActivity.class);
            startActivity(intent);
        });
    }

    private void navigateToActivity(Class<?> targetActivity) {
        Intent intent = new Intent(MainActivity.this, targetActivity);
        startActivity(intent);
    }
}
