package co.edu.unipiloto.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Registro extends AppCompatActivity implements View.OnClickListener {

    private EditText txtUsuario, txtNombre, txtEmail, txtContrasena, txtConfirmarContrasena;
    private Button btnRegistro;
    private RadioButton rBtnMas, rBtnFe;
    private DatabaseHelper baseDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        this.txtUsuario = (EditText) findViewById(R.id.txt_usuario);
        this.txtNombre = (EditText) findViewById(R.id.txt_nombre_completo);
        this.txtEmail = (EditText) findViewById(R.id.txt_email);
        this.txtContrasena = (EditText) findViewById(R.id.txt_contrasena);
        this.txtConfirmarContrasena = (EditText) findViewById(R.id.txt_confirmar_contrasena);
        this.rBtnMas = (RadioButton) findViewById(R.id.rBtn_masculino);
        this.rBtnFe = (RadioButton) findViewById(R.id.rBtn_femenino);
        this.btnRegistro = (Button) findViewById(R.id.btn_registro);
        this.baseDatos = new DatabaseHelper(this);

        this.btnRegistro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnRegistro.getId()){
            if(txtContrasena.getText().toString().equals(txtConfirmarContrasena.getText().toString())) {
                String nombre = txtNombre.getText().toString();
                String usuario = txtUsuario.getText().toString();
                String email = txtEmail.getText().toString();
                String genero= "N";
                if(rBtnMas.isChecked()){
                    genero = "M";
                }else if (rBtnFe.isChecked()){
                    genero = "F";
                }
                String contrasena = txtContrasena.getText().toString();

                if(this.baseDatos.insertar(new String[]{usuario, nombre, email,contrasena, genero})){
                    Toast.makeText(Registro.this, "Registro Completado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Registro.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}