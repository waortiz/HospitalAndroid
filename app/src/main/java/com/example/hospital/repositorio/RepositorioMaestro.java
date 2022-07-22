package com.example.hospital.repositorio;

import com.example.hospital.entidades.IMaestro;
import com.example.hospital.entidades.TipoDocumento;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMaestro {
    public static List<IMaestro> obtenerTiposDocumento() {
        List<IMaestro> tiposDocumento = new ArrayList<>() ;
        IMaestro cedula = new TipoDocumento();
        cedula.setId(1);
        cedula.setNombre("Cédula de Ciudadanía");
        tiposDocumento.add(cedula);
        IMaestro ti = new TipoDocumento();
        ti.setId(2);
        ti.setNombre("Tarjeta de Identidad");
        tiposDocumento.add(ti);

        return tiposDocumento;
    }

}
