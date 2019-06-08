package com.raymond.myrecipe;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        public TextView ctgTextView;
        public TextView nameTextView;

        public RcpViewHolder(View v) {
            super(v);
            nameTextView = v.findViewById(R.id.tv_item_name);
            ctgTextView = v.findViewById(R.id.tv_item_category);
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
        holder.nameTextView.setText(mDataset.get(position).name);
        holder.ctgTextView.setText(mDataset.get(position).category);
    }

    public int getItemCount() {
        return mDataset.size();
    }
}
