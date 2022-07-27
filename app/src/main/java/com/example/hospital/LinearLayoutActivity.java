package com.example.hospital;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hospital.adaptadores.MaestroAdapter;
import com.example.hospital.entidades.IMaestro;
import com.example.hospital.entidades.TipoDocumento;
import com.example.hospital.repositorio.RepositorioMaestro;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class LinearLayoutActivity extends AppCompatActivity {
    private Spinner spnTiposDocumento;
    private Button btnAceptar;
    private EditText txtNombre;
    private EditText txtTelefono;
    private EditText txtFechaNacimiento;
    private List<IMaestro> tiposDocumento;
    private IMaestro tipoDocumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        spnTiposDocumento = findViewById(R.id.spnTiposDocumento);
        btnAceptar = findViewById(R.id.btnAceptar);
        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento);
        txtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(LinearLayoutActivity.this,
                        com.google.android.material.R.style.MaterialAlertDialog_MaterialComponents_Picker_Date_Calendar,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar c = Calendar.getInstance();
                                c.set(year, month, dayOfMonth);

                                if(c.getTime().compareTo(Calendar.getInstance().getTime()) > 0) {
                                    txtFechaNacimiento.setText(null);
                                } else {
                                    txtFechaNacimiento.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                                }
                            }
                        }, c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH)
                );
                dialog.show();
            }
        });

        tiposDocumento = RepositorioMaestro.obtenerTiposDocumento();
        MaestroAdapter adapter = new MaestroAdapter(this, tiposDocumento);
        spnTiposDocumento.setAdapter(adapter);
        spnTiposDocumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipoDocumento = null;
                if (adapterView.getItemAtPosition(i) instanceof TipoDocumento) {
                    tipoDocumento = (TipoDocumento) adapterView.getItemAtPosition(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarDatos();
            }
        });
    }

    private void guardarDatos() {
        boolean error = false;
        txtNombre.setError(null);
        if(txtNombre.getText().toString().isEmpty())
        {
            txtNombre.setError("Debe ingresar el nombre");
            error = true;
        }
        if(txtTelefono.getText().toString().isEmpty())
        {
            txtNombre.setError("Debe ingresar el teléfono");
            error = true;
        }
        if(tipoDocumento == null)
        {
            error = true;
        }
        if(txtFechaNacimiento.getText().toString().isEmpty())
        {
            txtNombre.setError("Debe ingresar la fecha de nacimiento");
            error = true;
        }

        if(!error)  {
            new GuardarDatosAsincrono().execute();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(LinearLayoutActivity.this);
            builder.setMessage("Error en los datos. Por favor verifique").
                    setPositiveButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
            builder.show();
        }
    }

    private class GuardarDatosAsincrono extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            //Conecta a la base de datos

            return true;
        }

        @Override
        protected void onProgressUpdate(Void... voids) {

        }

        @Override
        protected void onPostExecute(Boolean result) {
            String mensaje;
            if(result) {
                mensaje = "Datos guardados exitosamente";
            } else {
                mensaje = "Error al guardar los datos";
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(LinearLayoutActivity.this);
            builder.setMessage(mensaje).
                    setPositiveButton("Aceptar",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
            builder.show();
        }

    }
}