package com.avesyarboles.avesdebenjaminaceval;


import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by brian_000 on 5/4/2017.
 */

public class BirdProfileFragment extends Fragment {
    public static final String ARG_ID = "ARG_ID";

    private final String DATABASE_NAME = "BirdDatabase";
    private final String DATABASE_VERSION = "";
    private final String TABLE_NAME = "bird_profiles";

    private int profileId;
    private final MediaPlayer mp = new MediaPlayer();

    public static BirdProfileFragment newInstance(int profileId) {
        Bundle args = new Bundle();
        args.putInt(ARG_ID, profileId);
        BirdProfileFragment fragment = new BirdProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileId = getArguments().getInt(ARG_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.activity_bird_profile, container, false);

        //add back button to the action bar
        //ActionBar actionBar = getActivity().getActionBar();
        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);

        SQLiteDatabase myDB = loadAndUpdateDB();

        TextView scientificNameTV = (TextView)view.findViewById(R.id.scientificNameTV);
        scientificNameTV.setText(getQueryValueByID(myDB, profileId, "SCIENTIFIC_NAME"));

        TextView birdCallTV = (TextView)view.findViewById(R.id.birdcallTV);
        birdCallTV.setText(Html.fromHtml("<b>"+getResources().getString(R.string.birdcall_label)+"</b> " + getQueryValueByID(myDB, profileId, "BIRD_CALL")));

        TextView commonNameTV = (TextView)view.findViewById(R.id.commonNameTV);
        commonNameTV.setText(getResources().getString(getResources().getIdentifier("name"+profileId, "string", getContext().getPackageName())));

        TextView birdSizeTV = (TextView)view.findViewById(R.id.birdSizeTV);
        birdSizeTV.setText(Html.fromHtml("<b>"+getResources().getString(R.string.bird_size_label)+"</b> " + getResources().getString(getResources().getIdentifier("size"+profileId, "string", getContext().getPackageName())) + " " + getResources().getString(R.string.unitsSize)));

        TextView birdDescriptionTV = (TextView)view.findViewById(R.id.birdDescriptionTV);
        birdDescriptionTV.setText(getResources().getString(getResources().getIdentifier("description"+profileId, "string", getContext().getPackageName())));

        ImageView birdImage = (ImageView)view.findViewById(R.id.birdIV);
        birdImage.setImageResource(getResources().getIdentifier("bird_image"+profileId, "drawable", getContext().getPackageName()));

        ImageButton soundButton = (ImageButton)view.findViewById(R.id.birdcallButton);
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
                    afd = getContext().getAssets().openFd("birdCalls/birdCall"+profileId+".mp3");
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

        return view;
    }

    private String getQueryValueByID(SQLiteDatabase myDB, int id, String columnName){
        Cursor profileRow = myDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE ID=" + id + ";", null);
        if(profileRow != null && profileRow.moveToFirst()) {
            int profileColumn = profileRow.getColumnIndex(columnName);
            return profileRow.getString(profileColumn);
        }else{
            return "ERROR";
        }
    }

    private SQLiteDatabase loadAndUpdateDB(){
        SQLiteDatabase myDB= null;

        XmlResourceParser birdProfileXML = getResources().getXml(R.xml.bird_profile);
        try {
            myDB = getContext().openOrCreateDatabase("BirdDatabase", MODE_PRIVATE, null);
            /* Create a Table in the Database. */
            myDB.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INT(4), SCIENTIFIC_NAME VARCHAR, BIRD_CALL VARCHAR);");

            /* Check to see if the table is empty, populate from xml if the table is empty */
            Cursor c = myDB.rawQuery("SELECT * FROM " + TABLE_NAME , null);
            if (c == null || !c.moveToFirst()) {
                int eventType = birdProfileXML.getEventType();
                int id = -1;
                String scientificName = "Género especie";
                String birdCall = "canto...canto";
                String tagName = null;
                while (eventType != XmlResourceParser.END_DOCUMENT) {

                    switch (eventType) {
                        case XmlResourceParser.START_TAG:
                            tagName = birdProfileXML.getName();
                            break;
                        case XmlPullParser.TEXT:
                            String innerText = birdProfileXML.getText();
                            if (tagName != null) {
                                switch (tagName) {
                                    case "ID":
                                        id = Integer.parseInt(innerText);
                                        break;
                                    case "SCIENTIFIC_NAME":
                                        scientificName = innerText;
                                        break;
                                    case "BIRD_CALL":
                                        birdCall = innerText;
                                        break;
                                }
                            }
                            break;
                        case XmlResourceParser.END_TAG:
                            tagName = birdProfileXML.getName();
                            if (tagName.equals("BIRD_PROFILE")) {
                            /* Insert data into Table*/
                                myDB.execSQL("REPLACE INTO " + TABLE_NAME
                                        + " (ID, SCIENTIFIC_NAME, BIRD_CALL)"
                                        + " VALUES (" + id + ", '" + scientificName + "', '" + birdCall + "');");
                            }
                            break;
                    }
                    eventType = birdProfileXML.next();
                }
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        /*retrieve data from database */
        /*Cursor c = myDB.rawQuery("SELECT * FROM " + TableName , null);

        int Column1 = c.getColumnIndex("ID");
        int Column2 = c.getColumnIndex("SCIENTIFIC_NAME");
        int Column3 = c.getColumnIndex("BIRD_CALL");

        // Check if our result was valid.
        if (c != null && c.moveToFirst()) {
            // Loop through all Results
            do {
                int id = c.getInt(Column1);
                String name = c.getString(Column2);
                String call = c.getString(Column3);
                Data =Data +id+"/"+name+"/"+call+"\n";
            }while(c.moveToNext());
        }
        Log.println(Log.INFO,"INFO",Data);*/
        return myDB;
    }
}
