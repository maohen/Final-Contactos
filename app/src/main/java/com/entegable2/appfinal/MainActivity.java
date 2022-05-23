package com.entegable2.appfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.entegable2.appfinal.bd.TransaccionesContactos;
import com.entegable2.appfinal.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MainActivity.this,NewContact.class));
                        finish();
                    }
                },100);
            }
        });

        listaContactos = findViewById((R.id.lista_contactos));
        listaContactos.setLayoutManager((new LinearLayoutManager(this)));

        TransaccionesContactos dbContactos = new TransaccionesContactos(MainActivity.this);
        dbContactos.datainicial();

        listaArrayContactos = new ArrayList<>();

        ListAdapter adapter = new ListAdapter(dbContactos.consultarContactos());

        listaContactos.setAdapter(adapter);
    }


}