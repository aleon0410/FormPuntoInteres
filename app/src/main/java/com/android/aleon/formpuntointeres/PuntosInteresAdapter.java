package com.android.aleon.formpuntointeres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LEONES on 29/09/2015.
 */
public class PuntosInteresAdapter extends ArrayAdapter<PuntoInteres> {

    private static class ViewHolder {
        TextView text1;
    }

    public PuntosInteresAdapter(Context context, ArrayList<PuntoInteres> puntosInteres) {
        super(context, android.R.layout.simple_dropdown_item_1line, puntosInteres);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PuntoInteres puntoInteres = getItem(position);

        ViewHolder viewHolder;

        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            viewHolder.text1 = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.text1.setText(puntoInteres.getNombre());

        return convertView;
    }

    @Override
    public long getItemId(int position)
    {
        return getItem(position).getId();
    }
}
