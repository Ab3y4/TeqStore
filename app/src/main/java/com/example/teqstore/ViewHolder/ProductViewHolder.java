package com.example.teqstore.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teqstore.Interface.ItemClickListener;
import com.example.teqstore.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListener listener;


    public ProductViewHolder(@NonNull View itemView)
    {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.productImage);
        txtProductName = (TextView) itemView.findViewById(R.id.productName);
        txtProductDescription = (TextView) itemView.findViewById(R.id.productDescription);
        txtProductPrice = (TextView) itemView.findViewById(R.id.productPrice);

    }

    public void setItemClickListner(ItemClickListener listner)
    {
        this.listener = listner;
    }


    @Override
    public void onClick(View view)
    {
        listener.onClick(view, getAdapterPosition(), false );
    }
}
