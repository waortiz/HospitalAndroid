package com.example.hospital.repositorio;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.hospital.converters.DateConverter;
import com.example.hospital.dao.IPacienteDao;
import com.example.hospital.entidades.Paciente;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Paciente.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class HospitalDataBase extends RoomDatabase {

    public abstract IPacienteDao pacienteDao();
    private static final String DATABASE_NAME = "paciente-db";

    private static HospitalDataBase INSTANCE;
    private static final int THREADS = 4;
    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);

    public static HospitalDataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (HospitalDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(), HospitalDataBase.class,
                                    DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
