package com.example.hospital.repositorio;

import android.content.Context;

import com.example.hospital.dao.IPacienteDao;
import com.example.hospital.entidades.Paciente;

import java.util.List;

public class RepositorioPaciente {
    private final IPacienteDao pacienteDao;

    public RepositorioPaciente(Context context) {
        HospitalDataBase db = HospitalDataBase.getInstance(context);
        pacienteDao = db.pacienteDao();
    }

    public List<Paciente> obtenerPacientes() {
        return pacienteDao.obtenerPacientes();
    }

    public void ingresarPaciente(Paciente paciente) {
        HospitalDataBase.dbExecutor.execute(
                () -> pacienteDao.ingresarPaciente(paciente)
        );
    }
}
