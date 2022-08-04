package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.VisitorViewHolder> {
    private ArrayList<VisitorItem> mVisitorList;

    public static class VisitorViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewLine1;
        public TextView mTextViewLine2;

        public VisitorViewHolder(View itemView) {
            super(itemView);
            mTextViewLine1 = itemView.findViewById(R.id.textview_line1);
            mTextViewLine2 = itemView.findViewById(R.id.textview_line_2);
        }
    }

    public VisitorAdapter(ArrayList<VisitorItem> visitorList) {
        mVisitorList = visitorList;
    }

    @Override
    public VisitorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.visitor_item, parent, false);
        VisitorViewHolder evh = new VisitorViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(VisitorViewHolder holder, int position) {
        VisitorItem currentItem = mVisitorList.get(position);

        holder.mTextViewLine1.setText(currentItem.getLine1());
        holder.mTextViewLine2.setText(currentItem.getLine2());
    }

    @Override
    public int getItemCount() {
        return mVisitorList.size();
    }
}
