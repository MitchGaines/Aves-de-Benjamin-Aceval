package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by brian_000 on 4/4/2017.
 *
 * Used to populate the Checklist for the list of birds.
 *
 * Este código genera el contenido para el Checklist de las aves.
 */

class ChecklistCursorAdapter extends ResourceCursorAdapter{

    public ChecklistCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    //Populate checklist entries using the project's resources, the bird's id, and the database.
    //Asignar las entradas del checklist, utilizando los recursos del proyecto, el id del ave, y el base de datos.
    public void bindView(View view, Context context, Cursor cursor) {
        TextView birdID = (TextView) view.findViewById(R.id.checklistBirdIDTV);
        int id = cursor.getInt(cursor.getColumnIndex("ID"));
        birdID.setText(""+id);

        TextView commonName = (TextView) view.findViewById(R.id.checklistCommonNameTV);
        try {
            commonName.setText(context.getResources().getString(context.getResources().getIdentifier("name" + id, "string", context.getPackageName())));
        }catch(Exception e){
            commonName.setText(R.string.error);
        }

        //Check the database and determine whether the box should be checked.
        //Verificar en el base de datos si la casilla debe ser llenada.
        CheckBox checkBox = (CheckBox)view.findViewById(R.id.birdCheckBox);
        int seen = cursor.getInt(cursor.getColumnIndex("SEEN"));
        if(seen > 0){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }
    }

}
