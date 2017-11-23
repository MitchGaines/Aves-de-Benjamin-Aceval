package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/*
* This class populates the information for a bird profile when it is selected from the list of birds.
*
* Este código asigna toda la información sobre un pérfil de un ave cuando está escogida de la lista de aves.
* */

public class BirdProfileActivity extends AppCompatActivity {
    //These constants are used to access the bird database.
    //Estas constantes son para el acceso al base de datos de las aves.
    private final String DATABASE_NAME = "BirdDatabase";
    private final String BIRD_TABLE_NAME = "bird_profiles";

    int profileId;
    final MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_profile);

        //Get the id of the bird to display information about.
        //Recibir el id del ave de la cual mostrará información.
        Intent intent = getIntent();
        profileId = intent.getIntExtra("id", 0);

        //Set the title and add the back button to the toolbar.
        //Asignar el título y añadir un botón al toolbar para regresar a la actividad previa.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(R.string.bird_profile_label);

        //Open and update the database.
        //Abrir y actualizar el base de datos.
        SQLiteDatabase myDB = loadAndUpdateDB();

        //Populate the activity's text from the database, using the bird's id.
        //Asignar el texto de la actividad con la información del base de datos y el id del ave.
        TextView scientificNameTV = (TextView)findViewById(R.id.scientificNameTV);
        scientificNameTV.setText(getQueryValueByID(myDB, profileId, "SCIENTIFIC_NAME"));

        TextView birdCallTV = (TextView)findViewById(R.id.birdcallTV);
        birdCallTV.setText(Html.fromHtml("<b>"+getResources().getString(R.string.birdcall_label)+"</b> " + getQueryValueByID(myDB, profileId, "BIRD_CALL")));

        //Populate the activity's text from the project's resources, using the bird's id.
        //Asignar el texto de los recursos del proyecto y el id del ave.

        TextView commonNameTV = (TextView) findViewById(R.id.commonNameTV);
        try {
            commonNameTV.setText(getResources().getString(getResources().getIdentifier("name" + profileId, "string", getPackageName())));
        }catch (Exception e){
            commonNameTV.setText(R.string.error);
        }

        TextView birdSizeTV = (TextView)findViewById(R.id.birdSizeTV);
        try {
            birdSizeTV.setText(Html.fromHtml("<b>"+getResources().getString(R.string.bird_size_label)+"</b> " + getResources().getString(getResources().getIdentifier("size"+profileId, "string", getPackageName())) + " " + getResources().getString(R.string.unitsSize)));
        }catch (Exception e){
            birdSizeTV.setText(R.string.error);
        }

        TextView birdDescriptionTV = (TextView)findViewById(R.id.birdDescriptionTV);
        try {
            birdDescriptionTV.setText(getResources().getString(getResources().getIdentifier("description" + profileId, "string", getPackageName())));
        }catch (Exception e){
            birdDescriptionTV.setText(R.string.error);
        }

        //Populate the bird's image from the project resources, using the bird's id.
        //Asignar la imagen del ave con los recursos del proyecto y el id del ave.

        ImageView birdImage = (ImageView)findViewById(R.id.birdIV);
        try {
            birdImage.setImageResource(getResources().getIdentifier("bird_image" + profileId, "drawable", getPackageName()));
        }catch (Exception e){
        }

        //Set up the button for playing the bird's call, using the project's resources.
        //Armar el botón para poner el canto del ave, utilizando los recursos del proyecto.
        ImageButton soundButton = (ImageButton)findViewById(R.id.birdcallButton);
        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying())
                {
                    mp.stop();
                }

                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("birdCalls/birdCall"+profileId+".mp3");
                    mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        if (myDB != null)
            myDB.close();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        recreate();
    }

    @Override
    //Ensure that the activity is in the right language when it is created.
    //Asegurar que la actividad está en el idioma correcto cuando está creado.
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(mp.isPlaying())
        {
            mp.stop();
        }
    }

    @Override
    //Handle clicks on the toolbar.
    //Manejar a cualqier click en el toolbar.
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

    //Used to get information about the bird from the database using the bird's id.
    //Conseguir información sobre un ave del base de datos, utilizando el id del ave.
    private String getQueryValueByID(SQLiteDatabase myDB, int id, String columnName){
        Cursor profileRow = myDB.rawQuery("SELECT * FROM " + BIRD_TABLE_NAME + " WHERE ID=" + id + ";", null);
        if(profileRow != null && profileRow.moveToFirst()) {
            int profileColumn = profileRow.getColumnIndex(columnName);
            return profileRow.getString(profileColumn);
        }else{
            return "ERROR";
        }
    }

    private SQLiteDatabase loadAndUpdateDB(){
        SQLiteDatabase myDB = this.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        return myDB;
    }
}
