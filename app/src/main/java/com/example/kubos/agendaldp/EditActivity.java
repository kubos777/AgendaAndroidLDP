package com.example.kubos.agendaldp;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    Button back,upd_el,del_btn;
    EditText name,lastname,address,phone,email;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        //Se instancían todos los elementos del layout
        back = (Button) findViewById(R.id.back2);
        upd_el = (Button) findViewById(R.id.upd_element);
        del_btn = (Button) findViewById(R.id.del_btn);
        name= (EditText) findViewById(R.id.name);
        lastname= (EditText) findViewById(R.id.lastname);
        address= (EditText) findViewById(R.id.address);
        email= (EditText) findViewById(R.id.email);
        phone= (EditText) findViewById(R.id.phone);
        //Se obtiene el intent y se asigna sus valores extra como texto de los EditText
        Intent i = getIntent();
        id = i.getLongExtra("id",0);
        name.setText(i.getStringExtra("name"));
        lastname.setText(i.getStringExtra("lastname"));
        phone.setText(i.getStringExtra("phone"));
        address.setText(i.getStringExtra("address"));
        email.setText(i.getStringExtra("email"));

        //Se pone a la escucha el botón para actualizar elemento
        upd_el.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length()>0 && phone.getText().toString().length()>=7){
                    //Se obtiene una instancia de Contacto y se actualiza a partir de sus métodos.
                    Contact c = new Contact(getBaseContext());
                    c.open();
                    c.updateContact(id, name.getText().toString(), lastname.getText().toString(), address.getText().toString(), email.getText().toString(), phone.getText().toString());
                    Toast.makeText(getBaseContext(), "El elemento ha sido actualizado.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getBaseContext(), "Ingresa por lo menos nombre y teléfono.",Toast.LENGTH_LONG).show();
                }
            }
        });
        //Botón que "termina" la actividad actual y regresa a la anterior
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Botón que elimina el contacto actual
        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Diálogo que avisa que se eliminará un contacto
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setTitle("Confirmación ");
                builder.setMessage("¿Está seguro de que desea eliminar el contacto?");

                builder.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
                    //Método que se activa si presionamos que sí queremos borrar
                    public void onClick(DialogInterface dialog, int which) {
                        //Se instancía un Contacto y se elimina a partir de sus métodos
                        Contact c = new Contact(getBaseContext());
                        c.open();
                        c.deleteContact(id);
                        finish();
                        dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Contacto eliminado.", Toast.LENGTH_LONG).show();
                    }

                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    //Método que se activa si presionamos que no queremos borrar
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                //Se crea y muestra la alerta
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }
}
