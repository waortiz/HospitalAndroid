package com.example.hospital.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.hospital.entidades.Paciente;

import java.util.List;

@Dao
public interface IPacienteDao {

    @Insert
    void ingresarPaciente(Paciente paciente);

    @Delete
    void eliminarPaciente(Paciente paciente);

    @Update
    void actualizarPaciente(Paciente paciente);

    @Query("select * from paciente")
    List<Paciente> obtenerPacientes();
}
