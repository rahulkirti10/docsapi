package com.example.docsapi;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderMain extends RecyclerView.ViewHolder
{
    TextView Name;
    ViewHolderMain(View itemView, final AdapterMain.OnItemClickListener listener)
    {
        super(itemView);
        Name=itemView.findViewById(R.id.doc_id);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                {
                    int position=getAdapterPosition();
                    if(position!=RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}