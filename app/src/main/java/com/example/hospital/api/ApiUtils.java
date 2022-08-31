package com.example.hospital.api;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "http://192.168.1.1/HospitalWebAPI/api/";

    public static IPacienteService getPacienteService() {

        return RetrofitClient.getClient(BASE_URL).create(IPacienteService.class);
    }

}
