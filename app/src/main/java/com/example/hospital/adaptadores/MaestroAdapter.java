package com.example.hospital.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hospital.R;
import com.example.hospital.entidades.IMaestro;

import java.util.List;

public class MaestroAdapter extends ArrayAdapter<IMaestro> {

    private static class ViewHolder {
        TextView textoSpinner;
    }

    public MaestroAdapter(@NonNull Context context, @NonNull List objects) {
        super(context, R.layout.spinner_row, 0, objects);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){

        IMaestro dataModel = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.spinner_row, parent, false);
            viewHolder.textoSpinner = convertView.findViewById(R.id.nombre);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textoSpinner.setText(dataModel.getNombre());

        return convertView;
    }
}
