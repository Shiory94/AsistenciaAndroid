package com.example.asus.proyectoasistencia;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registrarse extends AppCompatActivity {

    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
    }

    public void RegistrarseClick(View v) {
        if (v.getId() == R.id.btnRegistrar) {


            EditText nombre = (EditText) findViewById(R.id.etNombres);
            EditText apellidos = (EditText) findViewById(R.id.etApellidos);
            EditText usuario = (EditText) findViewById(R.id.etUsuario);
            EditText contraseña1 = (EditText) findViewById(R.id.etContraseña);
            EditText contraseña2 = (EditText) findViewById(R.id.etconContraseña);

            String nombrestr = nombre.getText().toString();
            String apellidosstr = apellidos.getText().toString();
            String usuariostr = usuario.getText().toString();
            String contraseña1str = contraseña1.getText().toString();
            String contraseña2str = contraseña2.getText().toString();

            if (!contraseña1str.equals(contraseña2str))

            {
                //mensaje de error
                Toast contra = Toast.makeText(Registrarse.this, "La contraseña no coincide!", Toast.LENGTH_SHORT);
                contra.show();

            } else {

                Register r = new Register();
                r.setNombre(nombrestr);
                r.setApellidos(apellidosstr);
                r.setUsuario(usuariostr);
                r.setContraseña(contraseña1str);

                helper.insertRegister(r);
                Toast contra = Toast.makeText(Registrarse.this, "Usuario registrado correctamente.", Toast.LENGTH_SHORT);
                contra.show();
                can(v);
            }

        }
    }

    public void can(View v) {
        Intent i = new Intent(Registrarse.this, Login.class);
        startActivity(i);
    }
}

