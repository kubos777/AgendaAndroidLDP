package com.example.kubos.agendaldp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewActivity extends AppCompatActivity {

    Button back,add_el;
    EditText name,lastname,address,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        //Se instancían los elementos de la interfaz
        back = (Button) findViewById(R.id.back2);
        add_el = (Button) findViewById(R.id.add_element);
        name= (EditText) findViewById(R.id.name);
        lastname= (EditText) findViewById(R.id.lastname);
        address= (EditText) findViewById(R.id.address);
        email= (EditText) findViewById(R.id.email);
        phone= (EditText) findViewById(R.id.phone);

        add_el.setOnClickListener(new View.OnClickListener() {
            //Método que se activa cuando se presiona el botón de agregar contacto
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length()>0 && phone.getText().toString().length()>=7){
                    //Se crea una instancia de contacto
                    Contact c = new Contact(getBaseContext());
                    c.open();
                    //Abrimos la escritura hacia la base de datos y creamos un contacto
                    c.createContact(name.getText().toString(), lastname.getText().toString(), address.getText().toString(), email.getText().toString(), phone.getText().toString());
                    name.setText("");
                    lastname.setText("");
                    address.setText("");
                    email.setText("");
                    phone.setText("");
                    Toast.makeText(getBaseContext(), "Se agregó un nuevo contacto.",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getBaseContext(), "Ingresa por lo menos nombre y teléfono.",Toast.LENGTH_LONG).show();
                }
            }
        });
        //Si se presiona el botón de regresar, se termina esta actividad
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
