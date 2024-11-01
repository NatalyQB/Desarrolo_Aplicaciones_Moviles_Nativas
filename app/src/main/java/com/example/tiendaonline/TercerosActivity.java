package com.example.tiendaonline;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TercerosActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etNit, etTelefono, etCorreo;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terceros);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etNit = findViewById(R.id.etNit);
        etTelefono = findViewById(R.id.etTelefono);
        etCorreo = findViewById(R.id.etCorreo);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etNombre.getText().toString().trim();
                String apellido = etApellido.getText().toString().trim();
                String nit = etNit.getText().toString().trim();
                String telefono = etTelefono.getText().toString().trim();
                String correo = etCorreo.getText().toString().trim();

                if (nombre.isEmpty() || apellido.isEmpty() || nit.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
                    Toast.makeText(TercerosActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    new CrearTerceroTask().execute(nombre, apellido, nit, telefono, correo);
                }
            }
        });
    }

    private class CrearTerceroTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String nombre = params[0];
            String apellido = params[1];
            String nit = params[2];
            String telefono = params[3];
            String correo = params[4];

            try {
                URL url = new URL("http://localhost/api_tienda/crear_terceros.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);

                String postData = "nombre=" + nombre + "&apellido=" + apellido + "&nit=" + nit + "&telefono=" + telefono + "&correo=" + correo;

                OutputStream os = conn.getOutputStream();
                os.write(postData.getBytes());
                os.flush();
                os.close();

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
                Toast.makeText(TercerosActivity.this, result, Toast.LENGTH_SHORT).show();
                if (result.equals("Registro exitoso")) {
                    finish();
                }
            } else {
                Toast.makeText(TercerosActivity.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
