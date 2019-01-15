package com.example.android.realestatemaster.HelperClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import static android.content.Context.MODE_PRIVATE;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private SharedPreferences liked;
    private Context context;
    private ProportyJsonModel data;
    private ErrorJsonModel errorData;
    private OnItemClickListener onItemClickListener;
    private OnFavoriteClickListener onFavoriteClickListener;
    private String searchString;
    ImageView favouriteImageButton;

    public RecyclerViewAdapter(Context context, ProportyJsonModel data, String searchString) {
        this.context = context;
        this.data = data;
        this.searchString = searchString;

    }

    public void setOnFavoriteClickListener(OnFavoriteClickListener listener, ImageView favouriteImageButton) {
        onFavoriteClickListener = listener;
        this.favouriteImageButton = favouriteImageButton;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
        String priceFormatString = decimalFormat.format(Double.parseDouble(data.getListing().get(position).getPrice()));
        String originalShortDesc = data.getListing().get(position).getShortDescription();
        if (priceFormatString.equals("0")) {
            holder.priceTv.setText(data.getListing().get(position).getPriceModifier());
        } else {
            holder.priceTv.setText("£ " + priceFormatString);
        }
        holder.shortDescriptionTv.setText(originalShortDesc.startsWith("<p") ? originalShortDesc.substring(15) : originalShortDesc);
        holder.bedroomTv.setText(data.getListing().get(position).getNumBedrooms());
        holder.bathroomTv.setText(data.getListing().get(position).getNumBathrooms());
        holder.addressTv.setText(data.getListing().get(position).getDisplayableAddress());
        holder.datePostedTv.setText(data.getListing().get(position).getFirstPublishedDate());
        holder.favouritesFab.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        SharedPreferences sharedPreferences = context.getSharedPreferences("LIKED_PROPERTIES",0);
        String listingId = data.getListing().get(position).getListingId();
        String modifiedSearchString = searchString+"&listing_id="+listingId;
        if (sharedPreferences.contains(listingId));
        if (sharedPreferences.getString(listingId,"").equals(modifiedSearchString)){
            holder.favouritesFab.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
        else {
            holder.favouritesFab.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }

        String proportyImageUrl = data.getListing().get(position).getImageUrl();
        String agentLogoUrl = data.getListing().get(position).getAgentLogo();
        initImageLoader();
        setProportyImage(proportyImageUrl, holder.proportyImage);
        setProportyImage(agentLogoUrl, holder.agentLogoImage);

    }

    @Override
    public int getItemCount() {

        return data == null || data.getListing() == null ? 0 : data.getListing().size();      //return if data in null
    }

    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(context);
        ImageLoader.getInstance().init(universalImageLoader.configureImageLoader());
    }

    private void setProportyImage(String imageUrl, ImageView imageView) {
        UniversalImageLoader.setImage(imageUrl, imageView, null, "");
    }

    public void toggleFavourites(ProportyJsonModel data, int position, SharedPreferences.Editor editor, SharedPreferences sharedPreferences, ViewHolder holder) {
        String propertyId = data.getListing().get(position).getListingId();
        if (!sharedPreferences.contains(propertyId)) {
            holder.favouritesFab.setImageResource(R.drawable.ic_favorite_black_24dp);
            Utilities.showToast(context, "Liked: " + position);
            editor.putString(propertyId, propertyId);
            editor.commit();
        } else {
            holder.favouritesFab.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            Utilities.showToast(context, "Unliked: " + position);
            editor.remove(propertyId);
            editor.commit();
        }

        Log.i("Liked", liked.getAll().toString());
    }

    public interface OnItemClickListener {
        void onItemClick(int clickedItemIndex);
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(int likedItemIndex,ImageView favoriteButton);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView priceTv, shortDescriptionTv, bedroomTv, bathroomTv, areaTv, datePostedTv, addressTv;
        ImageView proportyImage, agentLogoImage, favouritesFab;


        public ViewHolder(final View itemView) {
            super(itemView);
            priceTv = itemView.findViewById(R.id.price_tv_value_id);
            shortDescriptionTv = itemView.findViewById(R.id.short_description_tv_id);
            bedroomTv = itemView.findViewById(R.id.bedroom_tv_value_id);
            bathroomTv = itemView.findViewById(R.id.bathroom_tv_value_id);
            areaTv = itemView.findViewById(R.id.area_tv_value_id);
            addressTv = itemView.findViewById(R.id.address_tv_value_id);
            datePostedTv = itemView.findViewById(R.id.time_ago_tv_value_id);
            agentLogoImage = itemView.findViewById(R.id.agent_logo_image_view_id);
            proportyImage = itemView.findViewById(R.id.proporty_image_view_id);
            favouritesFab = itemView.findViewById(R.id.favourite_fab);

            favouritesFab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
                if (onFavoriteClickListener != null) {
                    int position = getAdapterPosition();
                  if (position != RecyclerView.NO_POSITION) {
                    onFavoriteClickListener.onFavoriteClick(position,favouritesFab);
                  }
        }
    }
});
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(position);
                        }
                    }
                }
            });


        }


    }

}

