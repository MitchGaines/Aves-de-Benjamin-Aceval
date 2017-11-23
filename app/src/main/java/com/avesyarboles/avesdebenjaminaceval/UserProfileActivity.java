package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class UserProfileActivity extends AppCompatActivity {

    private final String DATABASE_NAME = "BirdDatabase";
    private final String USER_INFO_TABLE_NAME = "user_info";
    private final String BIRD_SEEN_NAME = "bird_seen";

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        SQLiteDatabase myDB = loadAndUpdateDB();
        Cursor userInfo = myDB.rawQuery("SELECT * FROM " + USER_INFO_TABLE_NAME + " WHERE ID=" + 0 + ";", null);
        if(userInfo != null && userInfo.moveToFirst()) {
            int profileColumn = userInfo.getColumnIndex("NAME");
            username = userInfo.getString(profileColumn);
        }else{
            username = getString(R.string.name_placeholder);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.user_profile_label);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView usernameTV = (TextView)findViewById(R.id.usernameTV);
        usernameTV.setText(username);

        int total = 0, seen = 0;
        Cursor profileRow = myDB.rawQuery("SELECT * FROM " + BIRD_SEEN_NAME + ";", null);
        if(profileRow != null && profileRow.moveToFirst()) {
            boolean moveNext = true;
            while(profileRow != null && moveNext){
                total++;
                if(profileRow.getInt(profileRow.getColumnIndex("SEEN")) > 0){
                    seen++;
                }
                moveNext = profileRow.moveToNext();
            }
        }

        TextView speciesSeenTV = (TextView)findViewById(R.id.speciesSeenTV);
        speciesSeenTV.setText(getString(R.string.species_ratio_label, seen, total));

        int numLogros = 6;
        boolean uno = false, cinco = false, diez = false, veinte = false, treinticinco = false, cincuenta = false;

        if(userInfo != null && userInfo.moveToFirst()){
            for(int i=0; i<numLogros; i++){
                switch(i){
                    case 0:
                        int logroColumna = userInfo.getColumnIndex("LOGRO1");
                        uno = userInfo.getInt(logroColumna) != 0;
                        break;
                    case 1:
                        logroColumna = userInfo.getColumnIndex("LOGRO2");
                        cinco = userInfo.getInt(logroColumna) != 0;
                        break;
                    case 2:
                        logroColumna = userInfo.getColumnIndex("LOGRO3");
                        diez = userInfo.getInt(logroColumna) != 0;
                        break;
                    case 3:
                        logroColumna = userInfo.getColumnIndex("LOGRO4");
                        veinte = userInfo.getInt(logroColumna) != 0;
                        break;
                    case 4:
                        logroColumna = userInfo.getColumnIndex("LOGRO5");
                        treinticinco = userInfo.getInt(logroColumna) != 0;
                        break;
                    case 5:
                        logroColumna = userInfo.getColumnIndex("LOGRO6");
                        cincuenta = userInfo.getInt(logroColumna) != 0;
                        break;
                    default:
                        break;
                }
            }
        }

        GridLayout logrosGL = (GridLayout)findViewById(R.id.logrosGL);
        logrosGL.addView(new Logro(UserProfileActivity.this, getString(R.string.logro1_title), getString(R.string.logro1_description),R.mipmap.ic_bronze_medal, uno));
        logrosGL.addView(new Logro(UserProfileActivity.this, getString(R.string.logro2_title), getString(R.string.logro2_description),R.mipmap.ic_silver_medal, cinco));
        logrosGL.addView(new Logro(UserProfileActivity.this, getString(R.string.logro3_title), getString(R.string.logro3_description),R.mipmap.ic_gold_medal, diez));
        logrosGL.addView(new Logro(UserProfileActivity.this, getString(R.string.logro4_title), getString(R.string.logro4_description),R.mipmap.ic_bronze, veinte));
        logrosGL.addView(new Logro(UserProfileActivity.this, getString(R.string.logro5_title), getString(R.string.logro5_description),R.mipmap.ic_silver, treinticinco));
        logrosGL.addView(new Logro(UserProfileActivity.this, getString(R.string.logro6_title), getString(R.string.logro6_description),R.mipmap.ic_gold, cincuenta));


        for(int i=0; i < logrosGL.getChildCount(); i++){
            Logro l = (Logro)logrosGL.getChildAt(i);
            l.init();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_user_profile:
                return true;
            case android.R.id.home:
                //Write your logic here
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private SQLiteDatabase loadAndUpdateDB(){
        SQLiteDatabase myDB = this.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        return myDB;
    }
}
