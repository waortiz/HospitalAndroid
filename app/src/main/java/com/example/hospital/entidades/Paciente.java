package com.example.hospital.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "paciente")
public class Paciente {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private long id;

    @NonNull
    @ColumnInfo(name = "nombres")
    private String nombres;

    @NonNull
    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @NonNull
    @ColumnInfo(name = "telefono")
    private String telefono;

    @NonNull
    @ColumnInfo(name = "idTipoDocumento")
    private int idTipoDocumento;

    @NonNull
    @ColumnInfo(name = "numeroDocumento")
    private String numeroDocumento;

    @NonNull
    @ColumnInfo(name = "fechaNacimiento")
    private Date fechaNacimiento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getNombres() {
        return nombres;
    }

    public void setNombres(@NonNull String nombres) {
        this.nombres = nombres;
    }

    @NonNull
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NonNull String telefono) {
        this.telefono = telefono;
    }

    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    @NonNull
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(@NonNull String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @NonNull
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(@NonNull Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @NonNull
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@NonNull String apellidos) {
        this.apellidos = apellidos;
    }
}
