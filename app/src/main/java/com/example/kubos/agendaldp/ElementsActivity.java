package com.example.kubos.agendaldp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ElementsActivity extends ListActivity {

    Button back;
    Contact data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elements);

        back = (Button) findViewById(R.id.back);
        //Abrimos la base de datos para escribir a partir de Contact
        data = new Contact(this);
        data.open();

        //Se obtiene una lista con todos los valores disponibles en la tabla
        final List<Contact>  values = data.getAll();
        //Se crea un adaptador a lista a partir de un arreglo
        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_expandable_list_item_1,values);
        //Se crea un cursor para el listview a partir del adaptador
        setListAdapter(adapter);
        //Se asigna a la cada elemento su respectiva vista
        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //Método que se activa cuando presionamos en un elemento de la lista
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Creamos un intent y en sus extras enviamos todos los datos a EditActivity
                Intent i = new Intent(ElementsActivity.this,EditActivity.class);
                i.putExtra("id",values.get(position).id);
                i.putExtra("name",values.get(position).name);
                i.putExtra("lastname",values.get(position).lastname);
                i.putExtra("address",values.get(position).address);
                i.putExtra("email",values.get(position).email);
                i.putExtra("phone",values.get(position).phone);
                startActivity(i);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            //Método que se activa cuando presionamos el boton de regresar
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onResume()
    {  //Método que se ejecuta al regresar control a la actividad
        super.onResume();
        final List<Contact>  values = data.getAll();
        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
    }
}
