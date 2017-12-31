package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

/*
* This page displays information about the Karugua ha'e Tekove organization in Benjamín Aceval.
*
* Esta página contiene información sobre la organización Karugua ha'e Tekove de Benjamín Aceval.
*
* */

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //Set the title and add the back button to the toolbar.
        //Asignar el título y añadir un botón al toolbar para regresar a la actividad previa.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.about_us_title);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Set the text on the page about the organization
        //Asignar a la página el texto sobre la organización
        TextView aboutUsDescriptionTV = (TextView)findViewById(R.id.aboutUsDescriptionTV);
        aboutUsDescriptionTV.setText(R.string.about_us_description);
    }

    @Override
    //On loading the page, make sure it is in the right language.
    //Asegurar que la página está en el idioma correcto cuando la actividad es creada.
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    //Handle clicks on the toolbar.
    //Manejar a cualquier click en el toolbar.
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_user_profile:
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
