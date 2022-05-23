package com.entegable2.appfinal.bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.entegable2.appfinal.bd.ConexionBD;
import com.entegable2.appfinal.entidades.Contactos;

import java.util.ArrayList;

public class TransaccionesContactos extends ConexionBD {

    Context context;

    public TransaccionesContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public ArrayList<Contactos> consultarContactos(){
        ConexionBD database = new ConexionBD(context);
        SQLiteDatabase db = database.getReadableDatabase();

        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("select * from Contactos order by id desc", null);

        if(cursorContactos.moveToFirst()) {
            do{
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setCiudad(cursorContactos.getString(2));
                contacto.setProfesion(cursorContactos.getString(3));
                contacto.setTelefono(cursorContactos.getString(4));
                contacto.setEmail(cursorContactos.getString(5));
                listaContactos.add((contacto));
            } while (cursorContactos.moveToNext());
        }

        cursorContactos.close();
        return listaContactos;
    }

    public Contactos consultaContacto(int id){
        ConexionBD database = new ConexionBD(context);
        SQLiteDatabase db = database.getReadableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("select * from Contactos where id = " + id + " LIMIT 1", null);

        if(cursorContactos.moveToFirst()) {
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setCiudad(cursorContactos.getString(2));
            contacto.setProfesion(cursorContactos.getString(3));
            contacto.setTelefono(cursorContactos.getString(4));
            contacto.setEmail(cursorContactos.getString(5));
        }

        cursorContactos.close();
        return contacto;
    }

    public Long insertarContacto(String name, String city, String profession, String phone, String email){
        ConexionBD database = new ConexionBD(context);
        SQLiteDatabase dbWrite = database.getWritableDatabase();

        ContentValues insertar = new ContentValues();
        insertar.put("name", name);
        insertar.put("city", city);
        insertar.put("profesion", profession);
        insertar.put("phone", phone);
        insertar.put("email", email);

        Long id = dbWrite.insert("Contactos",null, insertar);

        return id;
    }

    public boolean eliminarContacto(int id) {

        boolean correcto = false;
        ConexionBD database = new ConexionBD(context);
        SQLiteDatabase db = database.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM Contactos WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public void datainicial(){
        ConexionBD database = new ConexionBD(context);
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor fila = db.rawQuery("select * from Contactos order by id desc", null);
        if(fila.moveToFirst()){
            return;
        }else{
            SQLiteDatabase dbWrite = database.getWritableDatabase();
            ContentValues insertar = new ContentValues();
            insertar.put("name", "Mis datos");
            insertar.put("city", "Medellin");
            insertar.put("profesion", "Desarrollador");
            insertar.put("phone", "3023334455");
            insertar.put("email", "mhenao@gmail.com");
            dbWrite.insert("Contactos",null, insertar);
            insertar.put("name", "Mendoza");
            insertar.put("city", "Rionegro");
            insertar.put("profesion", "Abogado");
            insertar.put("phone", "3003354488");
            insertar.put("email", "conabogados@gmail.com");
            dbWrite.insert("Contactos",null, insertar);
            insertar.put("name", "El fino");
            insertar.put("city", "Guayaco");
            insertar.put("profesion", "Alcoholico");
            insertar.put("phone", "3805006680");
            insertar.put("email", "elfino@gmail.com");
            dbWrite.insert("Contactos",null, insertar);
            insertar.put("name", "Chompiras");
            insertar.put("city", "Aranjuez");
            insertar.put("profesion", "Campanero");
            insertar.put("phone", "3003354488");
            insertar.put("email", "chompi@gmail.com");
            dbWrite.insert("Contactos",null, insertar);
            insertar.put("name", "El ñoño");
            insertar.put("city", "B. Antioquia");
            insertar.put("profesion", "Cantante");
            insertar.put("phone", "3103354490");
            insertar.put("email", "soloñoño@yahoo.com");
            dbWrite.insert("Contactos",null, insertar);
            insertar.put("name", "Tocino");
            insertar.put("city", "Bello");
            insertar.put("profesion", "Carnicero");
            insertar.put("phone", "3103354490");
            insertar.put("email", "elcarnudo@yahoo.com");
            dbWrite.insert("Contactos",null, insertar);
            insertar.put("name", "Moná");
            insertar.put("city", "Guayaco");
            insertar.put("profesion", "Guadañero");
            insertar.put("phone", "3103354490");
            insertar.put("email", "guadañasmona@yahoo.com");
            dbWrite.insert("Contactos",null, insertar);
            dbWrite.close();
        }

    }
}
