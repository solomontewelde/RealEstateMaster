package com.example.android.realestatemaster.HelperClasses;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.realestatemaster.R;
import com.example.android.realestatemaster.utils.GsonModel.ErrorJsonModel;
import com.example.android.realestatemaster.utils.GsonModel.Listing;
import com.example.android.realestatemaster.utils.GsonModel.ProportyJsonModel;
import com.example.android.realestatemaster.utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.realestatemaster.R;
import com.example.android.realestatemaster.utils.GsonModel.ErrorJsonModel;
import com.example.android.realestatemaster.utils.GsonModel.ProportyJsonModel;
import com.example.android.realestatemaster.utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RecyclerViewAdapterSmall extends RecyclerView.Adapter<RecyclerViewAdapterSmall.ViewHolder> {
    private Context context;
    private ProportyJsonModel data;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(int clickedItemIndex);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }
    public RecyclerViewAdapterSmall(Context context, ProportyJsonModel data){
        this.context = context;
        this.data = data;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.condensed_item_view,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




        DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
        String priceFormatString = decimalFormat.format(Double.parseDouble(data.getListing().get(position).getPrice()));
        String originalShortDesc = data.getListing().get(position).getShortDescription();
        if (priceFormatString.equals("0")){
            holder.priceTv.setText(data.getListing().get(position).getPriceModifier());
        }
        else{
            holder.priceTv.setText("£ "+priceFormatString);
        }
        holder.shortDescriptionTv.setText( originalShortDesc.startsWith("<p")?originalShortDesc.substring(15):originalShortDesc);
        holder.bedroomTv.setText(data.getListing().get(position).getNumBedrooms());
        holder.bathroomTv.setText(data.getListing().get(position).getNumBathrooms());
        holder.addressTv.setText(data.getListing().get(position).getDisplayableAddress());
        holder.datePostedTv.setText(data.getListing().get(position).getFirstPublishedDate());


        //holder.areaTv.setText((""+data.getListing().get(position).getFloorArea().getMaxFloorArea().getValue()+//TODO this area is null sometimes so it will cause a crash
        // ""+data.getListing().get(position).getFloorArea().getMaxFloorArea().getUnits()));


        String proportyImageUrl = data.getListing().get(position).getImageUrl();
        String agentLogoUrl = data.getListing().get(position).getAgentLogo();
        initImageLoader();
        setProportyImage(proportyImageUrl, holder.proportyImage);
        setProportyImage(agentLogoUrl, holder.agentLogoImage);

    }

    @Override
    public int getItemCount() {

        return data==null||data.getListing()==null?0:data.getListing().size();      //return if data in null
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView priceTv, shortDescriptionTv, bedroomTv, bathroomTv, areaTv,datePostedTv, addressTv;
        ImageView proportyImage, agentLogoImage;


        public ViewHolder(View itemView) {
            super(itemView);
            priceTv = itemView.findViewById(R.id.price_tv_value_id);
            shortDescriptionTv = itemView.findViewById(R.id.short_description_tv_id);
            bedroomTv = itemView.findViewById(R.id.bedroom_tv_value_id);
            bathroomTv = itemView.findViewById(R.id.bathroom_tv_value_id);
            areaTv = itemView.findViewById(R.id.area_tv_value_id);
            addressTv = itemView.findViewById(R.id.address_tv_value_id);
            datePostedTv = itemView.findViewById(R.id.time_ago_tv_value_id);
            agentLogoImage= itemView.findViewById(R.id.agent_logo_image_view_id);
            proportyImage = itemView.findViewById(R.id.proporty_image_view_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener !=null){
                        int position = getAdapterPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });

        }


    }
    private  void initImageLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(context);
        ImageLoader.getInstance().init(universalImageLoader.configureImageLoader());
    }

    private  void setProportyImage(String imageUrl, ImageView imageView){
        UniversalImageLoader.setImage(imageUrl, imageView, null, "");
    }

}
