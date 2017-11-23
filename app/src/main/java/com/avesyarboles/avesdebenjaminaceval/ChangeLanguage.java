package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This activity is used to allow users to change the app's displayed language within the application.
 *
 * Esta actividad permite a los usuarios cambiar el idioma de la aplicación sin cambiar el idioma de su dipositivo.
 */

public class ChangeLanguage extends AppCompatActivity {
    private Button saveChangesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.change_language_label);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        /**
         * Add all of the app's supported languages here, so that users can switch between them.
         *
         * Añadir todos los idiomas apoyados de la aplicación, para que los usuarios puedan cambiar entre ellos.
         */
        ArrayList<Language> languages = new ArrayList<Language>();
        languages.add(new Language(R.string.lang_es, R.mipmap.lang_es, "es"));
        languages.add(new Language(R.string.lang_en, R.mipmap.lang_en, "en"));

        String currentLanguageCode = LocaleHelper.getLanguage(getApplicationContext());
        int currentLanguagePosition = 0;
        for(int i=0; i < languages.size(); i++){
            if(languages.get(i).getLanguageCode().equals(currentLanguageCode)){
                currentLanguagePosition = i;
                break;
            }
        }

        final Spinner languageSpinner = (Spinner) findViewById(R.id.languageSpinner);
        LanguageSpinnerAdapter languageAdapter = new LanguageSpinnerAdapter(getApplicationContext(), languages);
        languageSpinner.setAdapter(languageAdapter);
        languageSpinner.setSelection(currentLanguagePosition);

        saveChangesButton = (Button) findViewById(R.id.changeLanguageButton);
        saveChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateViews(((Language)languageSpinner.getSelectedItem()).getLanguageCode());
            }
        });
    }

    private void updateViews(String languageCode) {
        Context context = LocaleHelper.setLocale(this, languageCode);
        Resources resources = context.getResources();

        recreate();
        Toast.makeText(getApplicationContext(),resources.getString(R.string.success),Toast.LENGTH_SHORT).show();
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
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
