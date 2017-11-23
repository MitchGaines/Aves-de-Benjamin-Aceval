package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String DATABASE_NAME = "BirdDatabase";
    private final String USER_INFO_TABLE_NAME = "user_info";
    private final String BIRD_TABLE_NAME = "bird_profiles";
    private final String BIRD_SEEN_NAME = "bird_seen";

    private final int WELCOME_INTENT_CODE = 504;

    private SQLiteDatabase myDB;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = loadAndUpdateDB();

        //use this to disable the application name from ActionBar
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("  " + getString(R.string.map_label));
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_USE_LOGO | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.mipmap.ic_karugua);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new PageFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this, actionBar));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        actionBar.setTitle("  "+getString(R.string.map_label));
                        break;
                    case 1:
                        actionBar.setTitle("  "+getString(R.string.bird_list_label));
                        break;
                    case 2:
                        actionBar.setTitle("  "+getString(R.string.checklist_label));
                        break;
                    case 3:
                        actionBar.setTitle("  "+getString(R.string.report_observation_label));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Give the TabLayout the ViewPager
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        for(int i=0; i < tabLayout.getTabCount(); i++){
            TabLayout.Tab t = tabLayout.getTabAt(i);
            switch(i){
                case 0:
                    t.setIcon(R.drawable.ic_action_map);
                    break;
                case 1:
                    t.setIcon(R.drawable.ic_action_list);
                    break;
                case 2:
                    t.setIcon(R.drawable.ic_action_checklist);
                    break;
                case 3:
                    t.setIcon(R.drawable.ic_action_report);
                    break;
                default:
                    t.setIcon(R.mipmap.lang_en);
                    break;
            }
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base));
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
                return true;
            case R.id.action_user_profile:
                Intent userProfileIntent = new Intent(getApplicationContext(), UserProfileActivity.class);
                startActivity(userProfileIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == WELCOME_INTENT_CODE) {
            if(resultCode == RESULT_OK){
                String name = data.getStringExtra("name");
                myDB.execSQL("REPLACE INTO " + USER_INFO_TABLE_NAME
                        + " (ID, NAME, LOGRO1, LOGRO2, LOGRO3, LOGRO4, LOGRO5, LOGRO6)"
                        + " VALUES (" + 0 + ", '" + name + "', 0, 0, 0, 0, 0, 0);");
                Toast.makeText(getApplicationContext(), getString(R.string.custom_welcome, name),Toast.LENGTH_LONG).show();
            }
            else{
                Intent welcomeIntent = new Intent(getApplicationContext(), StartupActivity.class);
                startActivityForResult(welcomeIntent, WELCOME_INTENT_CODE);
            }
        }
    }

    private SQLiteDatabase loadAndUpdateDB(){
        SQLiteDatabase myDB= this.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        XmlResourceParser birdProfileXML = getResources().getXml(R.xml.bird_profile);

        myDB.execSQL("CREATE TABLE IF NOT EXISTS " + USER_INFO_TABLE_NAME + " (ID INTEGER PRIMARY KEY, NAME VARCHAR, LOGRO1 INT(4), LOGRO2 INT(4), LOGRO3 INT(4), LOGRO4 INT(4), LOGRO5 INT(4), LOGRO6 INT(4));");
        Cursor c = myDB.rawQuery("SELECT * FROM " + USER_INFO_TABLE_NAME , null);
        if (c == null || !c.moveToFirst()) {
            //ask user for their name
            Intent welcomeIntent = new Intent(getApplicationContext(), StartupActivity.class);
            startActivityForResult(welcomeIntent, WELCOME_INTENT_CODE);
        }

        try {
            /* Create a Table in the Database. */
            myDB.execSQL("CREATE TABLE IF NOT EXISTS " + BIRD_TABLE_NAME + " (ID INTEGER PRIMARY KEY, SCIENTIFIC_NAME VARCHAR, BIRD_CALL VARCHAR);");
            myDB.execSQL("DELETE FROM " + BIRD_TABLE_NAME);

            /* Create a Table in the Database. */
            myDB.execSQL("CREATE TABLE IF NOT EXISTS " + BIRD_SEEN_NAME + " (ID INTEGER PRIMARY KEY, SEEN INT(4));");

            /* Check to see if the table is empty, populate from xml if the table is empty */
            //Cursor c = myDB.rawQuery("SELECT * FROM " + BIRD_TABLE_NAME , null);
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
                            myDB.execSQL("REPLACE INTO " + BIRD_TABLE_NAME
                                    + " (ID, SCIENTIFIC_NAME, BIRD_CALL)"
                                    + " VALUES (" + id + ", '" + scientificName + "', '" + birdCall + "');");

                            myDB.execSQL("INSERT OR IGNORE INTO " + BIRD_SEEN_NAME
                                    + " (ID, SEEN)"
                                    + " VALUES (" + id + ", 0);");
                        }
                        break;
                }
                eventType = birdProfileXML.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        myDB.execSQL("DELETE FROM " + BIRD_SEEN_NAME + " WHERE ID NOT IN (SELECT ID FROM " + BIRD_TABLE_NAME+")");

        return myDB;
    }
}
