package com.example.semana6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AResultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aresultado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Recibe los datos enviados desde Inicio
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("STNombre");
        Bitmap imagen = IntentCompat.getParcelableExtra(intent, "STImagen", Bitmap.class);

        TextView tvNombre = findViewById(R.id.TVNombreFoto);
        tvNombre.setText(nombre != null ? nombre : getString(R.string.sin_nombre));

        ImageView ivResultado = findViewById(R.id.IVResultado);
        if (imagen != null) {
            ivResultado.setImageBitmap(imagen);
        }

        Button btVolver = findViewById(R.id.BTVolver);
        btVolver.setOnClickListener(view -> finish());
    }
}
