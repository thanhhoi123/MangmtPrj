package com.example.quanlybanhang.viewmodel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quanlybanhang.R;
import com.example.quanlybanhang.data.model.Product;
import com.example.quanlybanhang.view.IonClick_rv;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;
    private Context mcontext;
    private IonClick_rv ionClick_rv;

    public ProductAdapter(List<Product> productList, Context mcontext, IonClick_rv ionClick_rv) {
        this.productList = productList;
        this.mcontext = mcontext;
        this.ionClick_rv = ionClick_rv;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tv_name_product.setText(product.getName());
        holder.tv_price_product.setText(product.getPrice());
        Glide.with(mcontext).load(product.getImage_link()).into(holder.imv_product);

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionClick_rv.onClickItem_rv(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imv_product;
        public TextView tv_name_product;
        public TextView tv_price_product;
        private CardView layout_item;
        public ViewHolder(View view) {
            super(view);
            imv_product = view.findViewById(R.id.imv_list_row_product);
            tv_name_product = view.findViewById(R.id.tv_list_row_name_product);
            tv_price_product = view.findViewById(R.id.tv_list_row_price_product);
            layout_item = view.findViewById(R.id.layout_item);
        }
    }
}
