package com.example.tiendaonline;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ListadoProductosActivity extends AppCompatActivity {

    private Button btnCrearProducto;
    private LinearLayout layoutProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_productos);

        btnCrearProducto = findViewById(R.id.btnCrearProducto);
        layoutProductos = findViewById(R.id.layoutProductos);


        new ListarProductosTask().execute();

        btnCrearProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListadoProductosActivity.this, CrearProductoActivity.class);
                startActivity(intent);
            }
        });
    }

    private class ListarProductosTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://localhost/api_tienda/listar_productos.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONArray productosArray = new JSONArray(result);
                    for (int i = 0; i < productosArray.length(); i++) {
                        JSONObject producto = productosArray.getJSONObject(i);

                        String codigoProducto = producto.getString("codigo");
                        String nombreProducto = producto.getString("nombre");
                        String precioProducto = producto.getString("valor");


                        View productoView = getLayoutInflater().inflate(R.layout.item_producto, layoutProductos, false);
                        TextView txtCodigoProducto = productoView.findViewById(R.id.txtCodigoProducto);
                        TextView txtNombreProducto = productoView.findViewById(R.id.txtNombreProducto);
                        TextView txtPrecioProducto = productoView.findViewById(R.id.txtPrecioProducto);


                        txtCodigoProducto.setText("Código: " + codigoProducto);
                        txtNombreProducto.setText("Nombre: " + nombreProducto);
                        txtPrecioProducto.setText("Precio: " + precioProducto);


                        layoutProductos.addView(productoView);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(ListadoProductosActivity.this, "Error al cargar productos", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ListadoProductosActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
