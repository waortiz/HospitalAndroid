package com.example.hospital.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidad {
    public static Date obtenerFecha(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
