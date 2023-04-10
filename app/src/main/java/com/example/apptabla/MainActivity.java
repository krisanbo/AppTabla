package com.example.apptabla;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText et_numero,et_nombre;
    private TextView tv_datos;
    private RadioButton rbSD,rbIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_numero= (EditText) findViewById(R.id.et_numero);
        tv_datos=(TextView) findViewById(R.id.tv_datos);
        rbSD=(RadioButton) findViewById(R.id.rbSD);
        rbIn=(RadioButton) findViewById(R.id.rbIN);
        et_nombre= (EditText) findViewById(R.id.et_nombre);



    }
    public void calcular(View view){
        String numero= et_numero.getText().toString();
        int numero_int= Integer.parseInt(numero);
        int calculo;
        String resultado="";

        for (int i=0;i<11;i++) {
            calculo = numero_int * i;
            resultado = resultado + numero_int + "x" + i + "=" + calculo + "\n";


            tv_datos.setText(resultado);
        }
    }
    public void guardar(View view){
        String numero= et_numero.getText().toString();
        String datos= tv_datos.getText().toString();
        String nombre= et_nombre.getText().toString();

        try {
            if(rbSD.isChecked()==true){

                File tarjetaSD = Environment.getExternalStorageDirectory();
                File rutaArchivo= new File(tarjetaSD.getPath(),nombre);
                // abrir el archivo
                OutputStreamWriter crearArchivo= new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));
                // guardamos los datos en el archivo
                crearArchivo.write(datos);
                // limpiamos el buffer:
                // quitame el puntero
                crearArchivo.flush();
                // cierrame el arhivo
                crearArchivo.close();
                // mostramos mensaje
                Toast.makeText(this, "el archivo" + nombre + " se ha guardado correctamente en la SD", Toast.LENGTH_SHORT).show();
                // para que se quede vacia la ventana de datos mandamos cadena vacia
                et_numero.setText("");
                tv_datos.setText("");
                et_nombre.setText("");

            }else if (rbIn.isChecked()== true){
                File In= Environment.getDataDirectory();
                File rutaArchivo= new File(In.getPath(),nombre);
                OutputStreamWriter crearArchivo= new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));
                crearArchivo.write(datos);
                crearArchivo.flush();
                crearArchivo.close();
                Toast.makeText(this, "el archivo" + nombre+ " se ha guardado correctamente en memoria interna", Toast.LENGTH_SHORT).show();
                et_numero.setText("");
                tv_datos.setText("");
            }
        }catch (IOException e){
            Toast.makeText(this, "no se puede grabar archivo", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultar(View view){

    }
}