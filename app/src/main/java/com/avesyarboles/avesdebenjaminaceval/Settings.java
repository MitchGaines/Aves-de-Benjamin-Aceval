package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Set;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.settings_label);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //get the resources that will be used to make the language selection row
        String currentLanguage;
        int currentLanguageIcon;
        switch (LocaleHelper.getLanguage(getApplicationContext())){
            case "en":
                currentLanguage = getString(R.string.lang_en);
                currentLanguageIcon = R.mipmap.lang_en;
                break;
            //default is spanish
            case "es":
            default:
                currentLanguage = getString(R.string.lang_es);
                currentLanguageIcon = R.mipmap.lang_es;
        }


        final ListView settingsLV = (ListView)findViewById(R.id.settingsLV);

        final ArrayList<Setting> settings = new ArrayList<Setting>();
        settings.add(new Setting(currentLanguage,currentLanguageIcon,ChangeLanguage.class));
        settings.add(new Setting(getString(R.string.about_us_title),R.drawable.ic_user_profile,AboutUsActivity.class));

        SettingsAdapter settingsAdapter = new SettingsAdapter(getApplicationContext(), settings);

        settingsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId)
            {
                switch(itemPosition){
                    case 0:
                    case 1:
                        Intent intent = new Intent(getApplicationContext(), settings.get(itemPosition).getActivity());
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });

        settingsLV.setAdapter(settingsAdapter);
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        recreate();
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
}
