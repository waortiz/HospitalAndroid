package com.example.hospital;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.hospital.adaptadores.MaestroAdapter;
import com.example.hospital.api.ApiUtils;
import com.example.hospital.api.IPacienteService;
import com.example.hospital.api.Respuesta;
import com.example.hospital.entidades.IMaestro;
import com.example.hospital.entidades.Paciente;
import com.example.hospital.entidades.TipoDocumento;
import com.example.hospital.repositorio.RepositorioMaestro;
import com.example.hospital.repositorio.RepositorioPaciente;
import com.example.hospital.utilidades.Utilidad;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LinearLayoutActivity extends AppCompatActivity {
    private Spinner spnTiposDocumento;
    private Button btnAceptar;
    private EditText txtNombres;
    private EditText txtApellidos;
    private EditText txtNumeroDocumento;
    private EditText txtTelefono;
    private EditText txtFechaNacimiento;
    private List<IMaestro> tiposDocumento;
    private IMaestro tipoDocumento;
    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        spnTiposDocumento = findViewById(R.id.spnTiposDocumento);
        btnAceptar = findViewById(R.id.btnAceptar);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtNumeroDocumento = findViewById(R.id.txtNumeroDocumento);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento);
        txtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(LinearLayoutActivity.this,
                        R.style.Theme_Hospital,
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
        txtNombres.setError(null);
        if(txtNombres.getText().toString().isEmpty())
        {
            txtNombres.setError("Debe ingresar el nombre");
            error = true;
        }
        if(txtTelefono.getText().toString().isEmpty())
        {
            txtTelefono.setError("Debe ingresar el teléfono");
            error = true;
        }
        if(tipoDocumento == null)
        {
            error = true;
        }
        if(txtFechaNacimiento.getText().toString().isEmpty())
        {
            txtFechaNacimiento.setError("Debe ingresar la fecha de nacimiento");
            error = true;
        }

        if(!error)  {
            paciente = new Paciente();
            paciente.setNombres(txtNombres.getText().toString());
            paciente.setApellidos(txtApellidos.getText().toString());
            paciente.setTelefono(txtTelefono.getText().toString());
            paciente.setNumeroDocumento(txtNumeroDocumento.getText().toString());
            paciente.setFechaNacimiento(Utilidad.obtenerFecha(txtFechaNacimiento.getText().toString()));
            paciente.setIdTipoDocumento(tipoDocumento.getId());
            new GuardarDatosAsincrono().execute();
        } else {
            Log.e("paciente","Error en los datos. Por favor verifique");
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
            RepositorioPaciente repositorioPaciente = new RepositorioPaciente(LinearLayoutActivity.this);
            repositorioPaciente.ingresarPaciente(paciente);

            return true;
        }

        @Override
        protected void onProgressUpdate(Void... voids) {

        }

        @Override
        protected void onPostExecute(Boolean result) {
            String mensaje;
            if(result) {
                guardarPacienteServicioExterno(paciente);
            } else {
                mostrarMensaje("Error al guardar los datos");
            }
        }
    }

    public void guardarPacienteServicioExterno(Paciente paciente) {
        String TAG = "Paciente";
        IPacienteService pacienteService = ApiUtils.getPacienteService();
        pacienteService.guardar(paciente).enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {

                if(response.isSuccessful()) {
                    mostrarMensaje("Paciente creado con consecutivo " + response.body().getId().toString());
                    Log.i(TAG, "Paciente creado con consecutivo " + response.body().getId().toString());
                }
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e(TAG, "No fue posible crear el paciente: " + t.getMessage());
            }
        });}

    public void mostrarMensaje(String texto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(texto).setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
        builder.show();
    }
}