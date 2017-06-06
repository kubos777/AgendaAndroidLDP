package com.example.kubos.agendaldp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * UNAM FI
 * Agenda movil, para la materia de LDP
 * Hecho por Vargas Castro Daniel y Chavez Delgado Jorge Luis
 */
public class Database extends SQLiteOpenHelper {
    //Se crean constantes útiles para operar la BD
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phonse";

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 4;

    //COnstante que contiene un string con la sentencia SQL para crear una BD
    private static final String DATABASE_CREATE = "create table "
            + TABLE_CONTACTS+ "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_NAME+ " text not null,"
            + COLUMN_LASTNAME+ " text not null,"
            + COLUMN_ADDRESS+ " text not null,"
            + COLUMN_EMAIL+ " text not null,"
            + COLUMN_PHONE+ " text not null"
            +");";

    //Método que crea la base de datos a partir del contexto y otros datos.
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Método que se ejecuta cuando se crea la BD. Sólo suele suceder una vez
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    //Método que se ejecuta cuando se cambia la versión de la base
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Database.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }
}
