package com.alanlomeli.prevencion_lugares.Adaptador;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alanlomeli.prevencion_lugares.Interfaz.ItemClickListener;
import com.alanlomeli.prevencion_lugares.Modelo.ObjetoRSS;
import com.alanlomeli.prevencion_lugares.R;

class FeedViewHolder extends RecyclerView.ViewHolder {//implements View.OnClickListener //View.OnLongClickListener{

        public TextView txtTitulo, txtFecha, txtContenido;
       // private ItemClickListener itemClickListener;

    public FeedViewHolder( View itemView) {
        super(itemView);
        txtTitulo = (TextView)itemView.findViewById(R.id.txtTitulo);
        txtFecha = (TextView)itemView.findViewById(R.id.txtFecha);
        txtContenido = (TextView)itemView.findViewById(R.id.txtContenido);

        //Set evento
       // itemView.setOnClickListener(this);
        //itemView.setOnLongClickListener(this);

    }
/*
   public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {

        itemClickListener.onClick(view,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),true);

        return true;
    }
*/
}
public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder>{

    private ObjetoRSS objetorss;
    private Context mCOntext;
    private LayoutInflater inflater;

    public FeedAdapter(ObjetoRSS objetorss, Context mCOntext) {
        this.objetorss = objetorss;
        this.mCOntext = mCOntext;
        inflater = LayoutInflater.from(mCOntext);
    }

    @Override
    public FeedViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View itemView = inflater.inflate(R.layout.fila,viewGroup,false);
        return new FeedViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder( FeedViewHolder feedViewHolder, int i) {
        feedViewHolder.txtTitulo.setText(objetorss.getItems().get(i).getTitle());
        feedViewHolder.txtFecha.setText(objetorss.getItems().get(i).getPubDate());
        //Quitar etiquetas html

        feedViewHolder.txtContenido.setText(Html.fromHtml(objetorss.getItems().get(i).getContent()).toString());
       /* feedViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(!isLongClick){
                Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse(objetorss.getItems().get(position).getLink()));
                mCOntext.startActivity(browserintent);
                }

            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return objetorss.items.size();
    }
}
