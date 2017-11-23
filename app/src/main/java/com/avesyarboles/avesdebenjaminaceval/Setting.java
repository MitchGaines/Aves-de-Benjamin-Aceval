package com.avesyarboles.avesdebenjaminaceval;

import android.graphics.drawable.Drawable;

/**
 * Created by brian_000 on 20/4/2017.
 */

public class Setting {
    public String name;
    public int iconId;
    public Class activity;

    public Setting(String name, int iconId, Class activity) {
        this.name = name;
        this.iconId = iconId;
        this.activity = activity;
    }

    public String getName(){
        return name;
    }

    public int getIconId(){
        return iconId;
    }

    public Class getActivity(){
        return activity;
    }
}
