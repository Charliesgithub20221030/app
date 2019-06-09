package com.raymond.myrecipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RcpAdapter extends RecyclerView.Adapter<RcpAdapter.RcpViewHolder> {
    private ArrayList<Recipe> mDataset;
    final private ListItemClickListener mOnClickListener;


    public RcpAdapter(ArrayList<Recipe> mDataSet, ListItemClickListener listener) {
        this.mDataset = mDataSet;
        this.mOnClickListener = listener;
        }

    public class RcpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public ImageView ctImageView;
//        public TextView ctgTextView;
        public TextView nameTextView;
        public ImageView lvImageView;

        public RcpViewHolder(View v) {
            super(v);
            ctImageView = v.findViewById(R.id.ct_iv);
            nameTextView = v.findViewById(R.id.tv_item_name);
//            ctgTextView = v.findViewById(R.id.tv_item_category);
            lvImageView = v.findViewById(R.id.level_iv);
            v.setOnClickListener(this);
        }

        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }

    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    public RcpAdapter.RcpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        RcpViewHolder viewHolder = new RcpViewHolder(view);

        return viewHolder;
    }

    public void onBindViewHolder(RcpViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        switch (mDataset.get(position).category) {
            case "Japanese Dish":
                holder.ctImageView.setImageResource(R.drawable.japanesedish);
                break;
            case "French Dish":
                holder.ctImageView.setImageResource(R.drawable.frenchdish);
                break;
            case "Korean Dish":
                holder.ctImageView.setImageResource(R.drawable.koreandish);
                break;
            case "Indonesian Dish":
                holder.ctImageView.setImageResource(R.drawable.indonesiandish);
                break;
            case "Chinese Dish":
                holder.ctImageView.setImageResource(R.drawable.chinesedish);
                break;
        }

        holder.nameTextView.setText(mDataset.get(position).name);

        switch (mDataset.get(position).level) {
            case 1:
                holder.lvImageView.setImageResource(R.drawable.level);
                break;
            case 2:
                holder.lvImageView.setImageResource(R.drawable.level2);
                break;
            case 3:
                holder.lvImageView.setImageResource(R.drawable.level3);
                break;
            case 4:
                holder.lvImageView.setImageResource(R.drawable.level4);
                break;
            case 5:
                holder.lvImageView.setImageResource(R.drawable.level5);
                break;
        }

//        holder.ctgTextView.setText(mDataset.get(position).category);
    }

    public int getItemCount() {
        return mDataset.size();
    }
}
