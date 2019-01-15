package com.example.android.realestatemaster.utils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.realestatemaster.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by 100043392 on 07-Apr-18.
 */

public class CustomSwipeAdapter extends PagerAdapter{
    private Context context;
    private LayoutInflater inflater;
    private String[] imageUrls;
    public CustomSwipeAdapter(Context context, String[] imageUrls){
        this.context = context;
        this.imageUrls = imageUrls;
    }
    @Override
    public int getCount() {
        return imageUrls.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = inflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = item_view.findViewById(R.id.image_view);
        TextView textView = item_view.findViewById(R.id.image_count);
        initImageLoader();
        setProportyImage(imageUrls[position],imageView);
        //imageView.setImageResource(resource[position]);
        textView.setText(""+(position+1)+"/"+imageUrls.length+" ");
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
    private  void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(context);
        ImageLoader.getInstance().init(universalImageLoader.configureImageLoader());
    }

    private  void setProportyImage(String imageUrl, ImageView imageView){
        UniversalImageLoader.setImage(imageUrl, imageView, null, "");
    }
}
