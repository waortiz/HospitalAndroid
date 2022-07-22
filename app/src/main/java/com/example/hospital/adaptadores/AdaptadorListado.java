package com.example.hospital.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.hospital.R;
import com.example.hospital.entidades.Maestro;

import java.util.List;

public class AdaptadorListado extends ArrayAdapter<Maestro> {

    public AdaptadorListado(@NonNull Context context, @NonNull List<Maestro> objects) {
        super(context, R.layout.spinner_row, R.id.nombre, objects);
    }

    private static class ViewHolder {
        TextView nombre;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        Maestro maestro = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.spinner_row, parent, false);
            viewHolder.nombre = convertView.findViewById(R.id.nombre);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.nombre.setText(maestro.getNombre());

        return convertView;
    }
}
