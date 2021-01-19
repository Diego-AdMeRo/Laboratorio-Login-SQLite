package co.edu.unipiloto.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ingreso extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsuario, txtContrasena;
    private Button btnIngreso, btnRegistro, btnTodo;
    private DatabaseHelper baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso);

        this.txtUsuario = (EditText) findViewById(R.id.txt_usuario);
        this.txtContrasena = (EditText) findViewById(R.id.txt_contrasena);
        this.btnIngreso = (Button) findViewById(R.id.btn_ingreso);
        this.btnRegistro = (Button) findViewById(R.id.btn_registro);
        this.btnTodo = (Button) findViewById(R.id.btn_obtenerTodo);

        this.btnIngreso.setOnClickListener(this);
        this.btnRegistro.setOnClickListener(this);
        this.btnTodo.setOnClickListener(this);

        this.baseDatos = new DatabaseHelper(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnIngreso.getId()) {
            String respuesta = this.baseDatos.ingreso(txtUsuario.getText().toString(), txtContrasena.getText().toString());
            if (respuesta.equals("Usuario Inexistente")) {
                Toast.makeText(Ingreso.this, "Usuario Inexistente", Toast.LENGTH_SHORT).show();
            } else if (respuesta.equals("Contraseña Incorrecta")) {
                Toast.makeText(Ingreso.this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Ingreso.this, respuesta, Toast.LENGTH_SHORT).show();
            }

        } else if (v.getId() == btnRegistro.getId()) {
            Intent registro = new Intent(Ingreso.this, Registro.class);
            startActivity(registro);
        } else if (v.getId() == btnTodo.getId()){
            Toast.makeText(Ingreso.this, this.baseDatos.obtenerTodo(), Toast.LENGTH_LONG).show();
        }
    }
}