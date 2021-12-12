package com.example.quanlybanhang.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quanlybanhang.R;
import com.example.quanlybanhang.data.model.ApiDetail;
import com.example.quanlybanhang.data.model.ApiHistory;
import com.example.quanlybanhang.data.model.ApiOrder;
import com.example.quanlybanhang.data.model.CurrentUser;
import com.example.quanlybanhang.data.model.ListApi;
import com.example.quanlybanhang.data.model.User;
import com.example.quanlybanhang.viewmodel.HistoryAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileFragment extends Fragment {

    private RecyclerView rvHistoryProduct;
    private HistoryAdapter historyAdapter;
    private List<ApiHistory> apiHistoryList;
    private List<ApiDetail> apiDetailList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        ImageView imvProfile = rootView.findViewById(R.id.imv_profile_person);
        TextView tvProfileName = rootView.findViewById(R.id.tv_profile_name);
        TextView tvProfileEmail = rootView.findViewById(R.id.tv_profile_email);
        TextView tvProfileSDT = rootView.findViewById(R.id.tv_profile_phone);
        TextView tvProfileAddress = rootView.findViewById(R.id.tv_profile_address);

        User user = CurrentUser.mUser;
        Glide.with(this).load(user.getAvatar()).into(imvProfile);
        tvProfileName.setText(user.getName());
        tvProfileSDT.setText(user.getSDT());
        tvProfileEmail.setText(user.getEmail());
        tvProfileAddress.setText(user.getAddress());

        rvHistoryProduct = rootView.findViewById(R.id.rv_profile_history_product);
        historyAdapter = new HistoryAdapter(apiDetailList,getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        rvHistoryProduct.setLayoutManager(gridLayoutManager);
        rvHistoryProduct.setAdapter(historyAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        rvHistoryProduct.addItemDecoration(itemDecoration);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quanlybanhangapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ListApi listApi = retrofit.create(ListApi.class);
        ApiHistory apiHistory = new ApiHistory(0, user.getId(), "","","","");
        Call<List<ApiHistory>> call = listApi.createHistory(apiHistory);
        call.enqueue(new Callback<List<ApiHistory>>() {
            @Override
            public void onResponse(Call<List<ApiHistory>> call, Response<List<ApiHistory>> response) {
                apiHistoryList = response.body();
                historyAdapter.notifyDataSetChanged();
                for(ApiHistory data: apiHistoryList){
                    Log.d("DEBUGORDER", data.getProductID() + " ");
                    ApiDetail apiDetail = new ApiDetail(data.getProductID(), 0,"","","",0,"","","");
                    Call<ApiDetail> call1 = listApi.createDetail(apiDetail);
                    call1.enqueue(new Callback<ApiDetail>() {
                        @Override
                        public void onResponse(Call<ApiDetail> call, Response<ApiDetail> response) {
//                            apiDetailList.add(response.body());
//                            historyAdapter.notifyDataSetChanged();
//                            Log.d("DEBUGDETAIL", response.body().getName() + " " + response.body().getCreated_at() + " "
//                            + response.body().getIDDH());
                        }

                        @Override
                        public void onFailure(Call<ApiDetail> call, Throwable t) {
                            Log.d("DEBUGDETAIl", t.getMessage() + " ");
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<List<ApiHistory>> call, Throwable t) {
                Log.d("DEBUGORDER",t.getMessage() + "");
            }
        });

        return  rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}