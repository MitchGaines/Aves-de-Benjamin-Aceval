package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by brian_000 on 4/4/2017.
 *
 * Used to populate the ListView for the list of birds.
 *
 * Este código genera el contenido para el ListView de las aves.
 */

class BirdCursorAdapter extends ResourceCursorAdapter{

    public BirdCursorAdapter(Context context, int layout, Cursor cursor, int flags) {
        super(context, layout, cursor, flags);
    }

    @Override
    //Set the text for each entry in the list to include their id number and their common name.
    //Asignar el texto para cada entrada en la lista a incluir su número de ID y su nombre común.
    public void bindView(View view, Context context, Cursor cursor) {
        TextView birdID = (TextView) view.findViewById(R.id.birdIDTV);
        int id = cursor.getInt(cursor.getColumnIndex("ID"));
        birdID.setText("" + id);

        TextView commonName = (TextView) view.findViewById(R.id.commonNameListTV);
        try {
            commonName.setText(context.getResources().getString(context.getResources().getIdentifier("name" + id, "string", context.getPackageName())));
        }catch(Exception e){
            commonName.setText(R.string.error);
        }
    }
}
