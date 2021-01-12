package com.example.docsapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<ViewHolderMain> {
    private OnItemClickListener mlistener;
    Context context;
    List<Document> list = new ArrayList<Document>();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mlistener = listener;
    }

    public AdapterMain(List<Document> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolderMain onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.mainlayout, parent, false);
        ViewHolderMain viewHolder = new ViewHolderMain(photoView, mlistener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolderMain viewHolder, final int position) {
        viewHolder.Name.setText("Document"+" "+list.get(position).getDoc_id());

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}


