package kalpesh.mac.com.raandroid_header.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import kalpesh.mac.com.raandroid_header.MainActivity;
import kalpesh.mac.com.raandroid_header.MapsActivity;
import kalpesh.mac.com.raandroid_header.R;
import kalpesh.mac.com.raandroid_header.model.Restaurant;
import kalpesh.mac.com.raandroid_header.utilities.ItemClickListener;

/**
 * Created by Iancu on 24/10/2016.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {
    private List<Restaurant> restaurants;
    private int rowLayout;
    private Context context;

    public RecyAdapter(List<Restaurant> rests,int rowLayout,Context contxt){
        this.restaurants =rests;
        this.rowLayout=rowLayout;
        this.context=contxt;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Restaurant restaurant =restaurants.get(position);
        holder.name.setText(restaurant.getName());
        holder.postcode.setText(restaurant.getAddress());
        Picasso.with(context).load(restaurant.getLogo().get(0).getStandardResolutionURL())
                .into(holder.img);
        holder.setClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Toast.makeText(context,"EEEEY "+restaurant.getName(), Toast.LENGTH_SHORT).show();
                Intent maps = new Intent(context, MapsActivity.class);
                Bundle b =new Bundle();
                b.putSerializable("item",restaurant);
                maps.putExtra("single",b);
                maps.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(maps);
            }
        });

    }


    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        public ItemClickListener clickListener;
        public TextView name;
        public TextView postcode;
        public ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView) itemView.findViewById(R.id.title);
            postcode=(TextView) itemView.findViewById(R.id.address);
            img =(ImageView) itemView.findViewById(R.id.thumbnail);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener clickListener){
            this.clickListener =clickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v,getPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v,getPosition(),true);
            return false;
        }
    }
}
