package com.avesyarboles.avesdebenjaminaceval;

import android.content.Context;
import android.support.annotation.Dimension;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * This code is used to allow for multiple images to be paged through on the same page.
 *
 * Este código permite que varias fotos puedan ser mostrado en el mismo espacio.
 */

public class ImageAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Integer> images;

    public ImageAdapter(Context context, ArrayList<Integer> images){
        this.context=context;
        this.images = images;
    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(images.get(position));
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}