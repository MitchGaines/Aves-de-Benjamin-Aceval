package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by brian_000 on 21/4/2017.
 *
 * This code is used to create the interface for changing languages in the application's settings.
 *
 * Este código crea la interfaz para cambiar idiomas dentro de los ajustes.
 */

public class LanguageSpinnerAdapter extends ArrayAdapter<Language> {
    public LanguageSpinnerAdapter(Context context, ArrayList<Language> languages) {
        super(context, 0, languages);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return rowview(position, convertView, parent);
    }

    private View rowview(int position, View convertView, ViewGroup parent){
        Language language = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_row, parent, false);
        }

        TextView settingNameTV = (TextView) convertView.findViewById(R.id.settingNameTV);
        ImageView settingIconIV = (ImageView) convertView.findViewById(R.id.settingIconIV);

        settingNameTV.setText(language.getLanguageNameId());
        settingIconIV.setImageResource(language.getLanguageIconId());

        return convertView;
    }
}
