package com.example.quanlybanhang.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlybanhang.R;
import com.example.quanlybanhang.data.model.ApiOrder;
import com.example.quanlybanhang.data.model.CurrentUser;
import com.example.quanlybanhang.data.model.ListApi;
import com.example.quanlybanhang.data.model.Product;
import com.example.quanlybanhang.data.model.User;
import com.example.quanlybanhang.viewmodel.OrderAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderFragment extends Fragment {

    private RecyclerView rvOrderProduct;
    private OrderAdapter orderAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        TextView tvOrderAddress = rootView.findViewById(R.id.tv_order_address);
        TextView tvOrderSDT = rootView.findViewById(R.id.tv_order_SDT);
        tvOrderAddress.setText(CurrentUser.mUser.getAddress());
        tvOrderSDT.setText(CurrentUser.mUser.getSDT());

        rvOrderProduct = rootView.findViewById(R.id.rv_order_list_product);
        Bundle bundle = this.getArguments();
        Product product = (Product) bundle.getSerializable("product");
        Integer amountOrder = bundle.getInt("amountOrder");
        orderAdapter = new OrderAdapter(product, product.getId(), amountOrder, getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        rvOrderProduct.setLayoutManager(gridLayoutManager);
        rvOrderProduct.setAdapter(orderAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        rvOrderProduct.addItemDecoration(itemDecoration);

        Button btnOrderDelete = rootView.findViewById(R.id.btn_order_delete);
        Button btnOrderOK = rootView.findViewById(R.id.btn_order_OK);

        btnOrderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnOrderOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = CurrentUser.mUser;
                postData(user.getId(), user.getAddress(),1,product.getId(),amountOrder);
            }
        });


        return rootView;
    }

    private void postData(Integer userID, String addressOrder, Integer accept, Integer idOrder, Integer amountOrder){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quanlybanhangapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ListApi listApi = retrofit.create(ListApi.class);
        ApiOrder apiOrder = new ApiOrder(userID, addressOrder,accept,idOrder,amountOrder);
        Call<ApiOrder> call = listApi.createOrder(apiOrder);
        call.enqueue(new Callback<ApiOrder>() {
            @Override
            public void onResponse(Call<ApiOrder> call, Response<ApiOrder> response) {
                Toast.makeText(getActivity(), "Đã đặt hàng thành công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ApiOrder> call, Throwable t) {
                Toast.makeText(getActivity(), "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}