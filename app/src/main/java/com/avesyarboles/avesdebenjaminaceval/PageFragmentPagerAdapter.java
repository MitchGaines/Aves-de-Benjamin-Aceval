package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by brian_000 on 4/4/2017.
 */

public class PageFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "", "", "", "" };
    private String tabTitles1[] = new String[] { "Map", "Profiles", "Checklist", "Report an Observation" };
    private Context context;
    private ActionBar actionBar;

    public PageFragmentPagerAdapter(FragmentManager fm, Context context, ActionBar actionBar) {
        super(fm);
        this.context = context;
        this.actionBar = actionBar;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                MapViewFragment mapFragment = new MapViewFragment();
                return mapFragment;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        return PageFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}