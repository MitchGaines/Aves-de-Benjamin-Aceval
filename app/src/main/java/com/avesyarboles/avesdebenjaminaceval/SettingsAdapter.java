package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by brian_000 on 20/4/2017.
 */

public class SettingsAdapter extends ArrayAdapter<Setting> {
    public SettingsAdapter(Context context, ArrayList<Setting> settings) {
        super(context, 0, settings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Setting setting = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_row, parent, false);
        }
        // Lookup view for data population
        TextView settingNameTV = (TextView) convertView.findViewById(R.id.settingNameTV);
        ImageView settingIconIV = (ImageView) convertView.findViewById(R.id.settingIconIV);
        // Populate the data into the template view using the data object
        settingNameTV.setText(setting.getName());
        settingIconIV.setImageResource(setting.getIconId());
        // Return the completed view to render on screen
        return convertView;
    }
}
