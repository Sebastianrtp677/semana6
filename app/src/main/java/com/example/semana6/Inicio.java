package com.example.semana6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Inicio extends AppCompatActivity {

    private ImageView ivFoto;
    private Bitmap miniatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ivFoto = findViewById(R.id.IVFoto);

        // Botón HACER FOTO: carga la imagen incluida en la app (sin usar la cámara)
        Button btHacerFoto = findViewById(R.id.BTHacerFoto);
        btHacerFoto.setOnClickListener(view -> {
            miniatura = BitmapFactory.decodeResource(getResources(), R.drawable.foto_ejemplo);
            ivFoto.setImageBitmap(miniatura);
        });

        // Botón ACEPTAR: captura el nombre y lo envía a AResultado (Intent explícito)
        final Button BTAceptar = findViewById(R.id.BTAceptar);
        BTAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText ETNombre = findViewById(R.id.ETNombreFoto);
                String StNombre = ETNombre.getText().toString().trim();

                if (StNombre.isEmpty()) {
                    ETNombre.setError(getString(R.string.error_nombre));
                    return;
                }

                Intent sIntent = new Intent(Inicio.this, AResultado.class);
                sIntent.putExtra("STNombre", StNombre);
                if (miniatura != null) {
                    sIntent.putExtra("STImagen", miniatura);
                }
                startActivity(sIntent);
            }
        });
    }
}
