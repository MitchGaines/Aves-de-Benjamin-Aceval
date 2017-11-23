package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class ObservationPointActivity extends AppCompatActivity {

    private String title;
    private String description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_point);

        Intent thisIntent = getIntent();
        title = thisIntent.getStringExtra("title");
        description = thisIntent.getStringExtra("description");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView descriptionTV = (TextView)findViewById(R.id.siteDescriptionTV);
        descriptionTV.setText(description);

        String pointLabel = getString(R.string.pointLabel);
        final String title1 = pointLabel+" 1";
        final String title2 = pointLabel+" 2";
        final String title3 = pointLabel+" 3";
        final String title4 = pointLabel+" 4";
        final String title5 = pointLabel+" 5";
        final String title6 = pointLabel+" 6";
        final String title7 = pointLabel+" 7";
        final String title8 = pointLabel+" 8";
        final String title9 = pointLabel+" 9";
        final String title10 = pointLabel+" 10";
        final String title11 = pointLabel+" 11";
        final String title12 = pointLabel+" 12";
        final String title13 = pointLabel+" 13";
        ArrayList<Integer> images = new ArrayList<Integer>();
        if(title.equals(title1)){
            images.add(R.drawable.letrero_1);
            images.add(R.drawable.point1);
        }else if(title.equals(title2)){
            images.add(R.drawable.letrero_2);
            images.add(R.drawable.point2);
        }else if(title.equals(title3)){
            images.add(R.drawable.letrero_3);
            images.add(R.drawable.point3);
        }else if(title.equals(title4)){
            images.add(R.drawable.letrero_4);
            images.add(R.drawable.point4);
        }else if(title.equals(title5)){
            images.add(R.drawable.letrero_5);
            images.add(R.drawable.point5);
        }else if(title.equals(title6)){
            images.add(R.drawable.letrero_6);
            images.add(R.drawable.point6);
        }else if(title.equals(title7)){
            images.add(R.drawable.letrero_7);
            images.add(R.drawable.point7);
        }else if(title.equals(title8)){
            images.add(R.drawable.letrero_8);
            images.add(R.drawable.point8);
        }else if(title.equals(title9)){
            images.add(R.drawable.letrero_9);
            images.add(R.drawable.point9);
        }else if(title.equals(title10)){
            images.add(R.drawable.letrero_10);
            images.add(R.drawable.point10);
        }else if(title.equals(title11)){
            images.add(R.drawable.letrero_11);
            images.add(R.drawable.point11);
        }else if(title.equals(title12)){
            images.add(R.drawable.letrero_12);
            images.add(R.drawable.point12);
        }else if(title.equals(title13)){
            images.add(R.drawable.letrero_13);
            images.add(R.drawable.point13_0);
            images.add(R.drawable.point13_1);
            images.add(R.drawable.point13_2);
        }else{
        }
        ViewPager observationPointVP = (ViewPager)findViewById(R.id.observationPointVP);
        ImageAdapter imageAdapter = new ImageAdapter(ObservationPointActivity.this, images);
        observationPointVP.setAdapter(imageAdapter);
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
