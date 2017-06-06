package com.example.kubos.agendaldp;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * UNAM FI
 * Agenda movil, para la materia de LDP
 * Hecho por Vargas Castro Daniel y Chavez Delgado Jorge Luis
 */
public class Contact {
    //Atributos modificables del elemento en la BD
    public long id;
    public String name;
    public String lastname;
    public String address;
    public String email;
    public String phone;
    private SQLiteDatabase database; //Objeto útil para manipulación de tablas en la BD
    private Database dbHelper; //Objeto de clase creada por nosotros para manipulación de la BD
    //Arreglo de cadenas que almacena toda una fila de una tabla (datos de un contacto)
    private String[] allColumns = { Database.COLUMN_ID,
            Database.COLUMN_NAME,
            Database.COLUMN_LASTNAME,
            Database.COLUMN_ADDRESS,
            Database.COLUMN_EMAIL,
            Database.COLUMN_PHONE};

    public long getId() {
        return id;
    }

    //Método que desplegará nombres en la lista de los contactos.
    @Override
    public String toString() {
        return name + " "+lastname;
    }

    //Se instancía un objeto de la clase que estamos creando a partir de este contexto
    public Contact(Context context) {
        dbHelper = new Database(context);
    }

    //Método que nos permite abrir la base de datos para su escritura
    public void open() throws SQLException {
        //Se asigna la base de datos modificable a la variable database
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //Método que nos permite crear un contacto a partir de lo que le pasemos en NewActivity
    public Contact createContact(String name,String lastname, String address, String email, String phone) {
        //ContentValues nos permite almacenar un set de valores
        ContentValues values = new ContentValues();
        //Le decimos a values qué valor se agregará en qué columna
        values.put(Database.COLUMN_NAME, name);
        values.put(Database.COLUMN_LASTNAME, lastname);
        values.put(Database.COLUMN_ADDRESS, address);
        values.put(Database.COLUMN_EMAIL, email);
        values.put(Database.COLUMN_PHONE, phone);
        //Con el método insert agregamos los valores a la BD
        //Además, el método "insert" regresa el id, por eso lo almacenamos
        long insertId = database.insert(Database.TABLE_CONTACTS, null, values);
        //Creamos un cursor para desplazarnos por las filas de la tabla con "query" con el ID dado
        Cursor cursor = database.query(Database.TABLE_CONTACTS,
                allColumns, Database.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Contact newContact= cursorToContact(cursor);
        //Cerramos el cursor
        cursor.close();
        return newContact;
    }

    //Método para actualizar contactos a partir de los datos pasados por EditActivity
    public void updateContact(long id, String name, String lastname, String address, String email, String phone) {
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_NAME, name);
        values.put(Database.COLUMN_LASTNAME, lastname);
        values.put(Database.COLUMN_ADDRESS, address);
        values.put(Database.COLUMN_EMAIL, email);
        values.put(Database.COLUMN_PHONE, phone);
        String where = "id=?"; //Permite que whereargs se utilice en vez de where
        String[] whereargs = new String[]{String.valueOf(id)};
        long insertId = database.update(Database.TABLE_CONTACTS,
                values,where,whereargs );
    }

    //Se elimina un contacto a partir de su ID.
    public void deleteContact(long id) {
        System.out.println("Contact deleted with id: " + id);
        database.delete(Database.TABLE_CONTACTS, Database.COLUMN_ID
                + " = " + id, null);
    }

    //Método que nos devuelve una lista con todos los contactos
    public List<Contact> getAll() {
        //Creamos un objeto de tipo lista que contiene contactos
        List<Contact> comments = new ArrayList<Contact>();

        //Creamos un cursor sobre la tabla de contactos
        Cursor cursor = database.query(Database.TABLE_CONTACTS,
                allColumns, null, null, null, null, null);

        //Nos desplazamos por la tabla y agregamos los elementos a la lista
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Contact contact= cursorToContact(cursor);
            comments.add(contact);
            cursor.moveToNext();
        }
        //Debemos cerrar el cursor
        cursor.close();
        return comments;
    }

    //Método que nos devuelve los datos de un contacto utilizando un cursor
    private Contact cursorToContact(Cursor cursor) {
        Contact c = new Contact(null);
        //Obtenemos los valores a partir de su número de columna
        c.id = cursor.getLong(0);
        c.name = cursor.getString(1);
        c.lastname = cursor.getString(2);
        c.address = cursor.getString(3);
        c.phone = cursor.getString(4);
        c.email = cursor.getString(5);
        return c;
    }
}
