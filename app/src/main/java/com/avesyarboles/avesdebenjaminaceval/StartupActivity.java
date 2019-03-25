package com.avesyarboles.avesdebenjaminaceval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Description:
 *  This class handles what happens on app startup. Is the
 *  app opening for the first time? Or has someone used this
 *  before?
 *
 *  UPDATE 3/25 by @Mitch Gaines
 *      I have removed a lot of functionality here. No reason
 *      to get user name so this simply bypasses to main
 *      activity state.
 */
public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
