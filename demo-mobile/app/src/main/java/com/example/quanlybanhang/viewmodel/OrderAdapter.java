package com.example.quanlybanhang.viewmodel;

import android.content.Context;
import android.util.Log;
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

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Product productOrder;
    private Integer idOrder;
    private Integer amountOrder;
    private Context mcontext;

    public OrderAdapter(Product productOrder, Integer idOrder, Integer amountOrder, Context mcontext) {
        this.productOrder = productOrder;
        this.idOrder = idOrder;
        this.amountOrder = amountOrder;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_row_item, parent, false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mcontext).load(productOrder.getImage_link()).into(holder.imvOrderProduct);
        holder.tvOrderName.setText(productOrder.getName());
        holder.tvOrderAmount.setText(amountOrder + "");
        Integer totalPrice = Integer.parseInt(productOrder.getPrice()) * amountOrder;
        holder.tvOrderTotalPrice.setText(totalPrice + "");
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imvOrderProduct;
        public TextView tvOrderName;
        public TextView tvOrderAmount;
        public TextView tvOrderTotalPrice;
        public ViewHolder(View view) {
            super(view);
            imvOrderProduct = view.findViewById(R.id.imv_order_row_product);
            tvOrderName = view.findViewById(R.id.tv_order_row_name_product);
            tvOrderAmount = view.findViewById(R.id.tv_order_row_name_amount);
            tvOrderTotalPrice = view.findViewById(R.id.tv_oder_row_name_price);
        }
    }
}
