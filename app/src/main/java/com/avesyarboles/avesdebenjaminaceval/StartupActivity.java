package com.avesyarboles.avesdebenjaminaceval;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        Button continueButton = (Button)findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameET = (EditText)findViewById(R.id.nameET);
                if(nameET.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),R.string.name_empty_message,Toast.LENGTH_LONG).show();
                }
                else {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("name", nameET.getText().toString());
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }
}
