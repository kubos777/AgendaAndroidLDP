package com.example.kubos.agendaldp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Botones de ver y agregar contactos
    Button all, n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtención de objetos para los botones del layout
        all = (Button)findViewById(R.id.elements);
        n = (Button)findViewById(R.id.new_element);

        //Método onclick para el botón de desplegar contactos
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se lanza un intent que manda a la activity con todos los elementos
                Intent i = new Intent(MainActivity.this, ElementsActivity.class);
                startActivity(i);
            }
        });
        //Método onclick para el botón de agregar contacto
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se lanza un intent que manda a la activity de crear un contacto
                Intent i = new Intent(MainActivity.this, NewActivity.class);
                startActivity(i);
            }
        });
    }
}
