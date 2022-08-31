package com.example.hospital.api;

import com.example.hospital.entidades.Paciente;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IPacienteService {

    @POST("paciente")
    Call<Respuesta> guardar(@Body Paciente paciente);

    @GET("paciente")
    Call<ArrayList<Paciente>> obtener();
}

