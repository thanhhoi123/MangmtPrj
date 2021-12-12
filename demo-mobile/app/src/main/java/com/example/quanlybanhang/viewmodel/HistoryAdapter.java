package com.example.quanlybanhang.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlybanhang.R;
import com.example.quanlybanhang.data.model.ApiDetail;
import com.example.quanlybanhang.data.model.ApiHistory;
import com.example.quanlybanhang.data.model.ApiOrder;
import com.example.quanlybanhang.data.model.ListApi;
import com.example.quanlybanhang.data.model.Product;
import com.example.quanlybanhang.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<ApiDetail> apiDetailList;
    private ApiDetail mApiDetail;
    private Context mContext;

    public HistoryAdapter(List<ApiDetail> apiDetailList, Context mContext) {
        this.apiDetailList = apiDetailList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_history_item, parent, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApiDetail apiDetail = apiDetailList.get(position);
        holder.tvHistoryName.setText(apiDetail.getName());
        holder.tvHistoryTime.setText(apiDetail.getCreated_at());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHistoryName;
        public TextView tvHistoryTime;
        public ViewHolder(View view) {
            super(view);
            tvHistoryName = view.findViewById(R.id.tv_profile_row_name_product);
            tvHistoryTime = view.findViewById(R.id.tv_profile_row_time);
        }
    }
}
