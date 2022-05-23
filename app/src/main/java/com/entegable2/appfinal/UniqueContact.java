package com.entegable2.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.entegable2.appfinal.bd.TransaccionesContactos;
import com.entegable2.appfinal.entidades.Contactos;

public class UniqueContact extends AppCompatActivity {

    TextView txtName, txtCity, txtProfession, txtPhone, txtEmail;
    Button btnBack, btnDelete;

    Contactos contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unique_contact);

        txtName = findViewById(R.id.nameUnique);
        txtCity = findViewById(R.id.ciudadUnique);
        txtProfession = findViewById(R.id.profesionUnique);
        txtPhone = findViewById(R.id.telefonoUnique);
        txtEmail = findViewById(R.id.emailUnique);

        btnDelete = findViewById(R.id.btnDelete);
        btnBack = findViewById(R.id.btnBackUnique);

        //recibir variable id
        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        TransaccionesContactos dbContactos = new TransaccionesContactos(UniqueContact.this);
        contacto = dbContactos.consultaContacto(id);

        if (contacto != null){
            txtName.setText(contacto.getNombre());
            txtCity.setText(contacto.getCiudad());
            txtProfession.setText(contacto.getProfesion());
            txtPhone.setText(contacto.getTelefono());
            txtEmail.setText(contacto.getEmail());
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean resultado = dbContactos.eliminarContacto(id);

               if(resultado){
                   Toast.makeText(UniqueContact.this, "CONTACTO ELIMINADO CON EXITO", Toast.LENGTH_LONG).show();
                   startActivity(new Intent(UniqueContact.this,MainActivity.class));
                   finish();
               }else {
                   Toast.makeText(UniqueContact.this, "Error al eliminar el registro", Toast.LENGTH_LONG).show();
               }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UniqueContact.this,MainActivity.class));
                finish();
            }
        });

    }
}