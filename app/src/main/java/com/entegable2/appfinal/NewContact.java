package com.entegable2.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.entegable2.appfinal.bd.TransaccionesContactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewContact extends AppCompatActivity {

    EditText txtName, txtCity, txtProfession, txtPhone, txtEmail;
    Button btnBack, btnSave;
    TransaccionesContactos dbContactos = new TransaccionesContactos(NewContact.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        txtName = findViewById(R.id.txtName);
        txtCity = findViewById(R.id.txtCity);
        txtProfession = findViewById(R.id.txtProfession);
        txtPhone = findViewById(R.id.editTextPhone);
        txtEmail = findViewById(R.id.editTextEmail);

        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean campos = validarCampos();
                if(campos == false){
                    return;
                }else {
                    Long id = dbContactos.insertarContacto(txtName.getText().toString(), txtCity.getText().toString(), txtProfession.getText().toString(), txtPhone.getText().toString(), txtEmail.getText().toString());
                    if (id > 0) {
                        Toast.makeText(NewContact.this, "CONTACTO GUARDADO CON EXITO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NewContact.this, "Error al intentar crear el registro", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewContact.this,MainActivity.class));
                finish();
            }
        });
    }

    private boolean validarCampos(){
        String nombre =  txtName.getText().toString();
        String ciudad = txtCity.getText().toString();
        String profesion = txtProfession.getText().toString();
        String telefono = txtPhone.getText().toString();
        String email =  txtEmail.getText().toString();

        if (nombre.equals("") || ciudad.equals("") || profesion.equals("") || telefono.equals("") || email.equals("")) {
            Toast.makeText(this, "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }

    private void limpiar(){
        txtName.setText("");
        txtCity.setText("");
        txtProfession.setText("");
        txtPhone.setText("");
        txtEmail.setText("");

    }
}