package com.example.quanlybanhang.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quanlybanhang.R;
import com.example.quanlybanhang.data.model.Product;

public class DetailFragment extends Fragment {

    Product product;
    View rootView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        ImageView imvDetailProduct = rootView.findViewById(R.id.imv_detail_product);
        TextView tvDetailName = rootView.findViewById(R.id.tv_detail_name_product);
        TextView tvDetailPrice = rootView.findViewById(R.id.tv_detail_price_product);
        TextView tvDetailAmount = rootView.findViewById(R.id.tv_detail_amount_product);
        TextView tvDetailDescription = rootView.findViewById(R.id.tv_detail_description_product);

        Bundle bundle = this.getArguments();
        Product product = (Product) bundle.getSerializable("product");
        Glide.with(this).load(product.getImage_link()).into(imvDetailProduct);
        tvDetailName.setText(product.getName());
        tvDetailPrice.setText(product.getPrice());
        tvDetailAmount.setText(product.getAmount() + "");
        tvDetailDescription.setText(product.getDescription());

        Button btnOrder = rootView.findViewById(R.id.btn_detail_order);
        EditText edtAmount = rootView.findViewById(R.id.edt_detail_amount);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", product);
                bundle.putInt("amountOrder", Integer.parseInt(edtAmount.getText().toString()));
                OrderFragment orderFragment  = new OrderFragment();
                orderFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, orderFragment).commit();
                Toast.makeText(getActivity(),"Đã thêm vào giỏ hàng",Toast.LENGTH_SHORT).show();

            }
        });
        return  rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}