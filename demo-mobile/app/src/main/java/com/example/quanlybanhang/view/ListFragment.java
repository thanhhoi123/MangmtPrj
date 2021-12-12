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
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.quanlybanhang.R;
import com.example.quanlybanhang.data.model.Product;
import com.example.quanlybanhang.viewmodel.ProductAdapter;
import com.example.quanlybanhang.viewmodel.ProductApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFragment extends Fragment {

    private ProductApiService apiService;
    private RecyclerView rvListProduct;
    private ArrayList<Product> productArrayList;
    private ProductAdapter productAdapter;
    private SearchView svProduct;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvListProduct = view.findViewById(R.id.rv_list_list_product);
        productArrayList = new ArrayList<Product>();
        productAdapter = new ProductAdapter(productArrayList, getActivity(), new IonClick_rv() {
            @Override
            public void onClickItem_rv(Product product) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("product", product);
                DetailFragment detailFragment = new DetailFragment();
                detailFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, detailFragment).commit();
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        rvListProduct.setLayoutManager(gridLayoutManager);
        rvListProduct.setAdapter(productAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        rvListProduct.addItemDecoration(itemDecoration);

        loadApi();

        //SearchView
        svProduct = view.findViewById(R.id.sv_list_product);
        svProduct.setImeOptions(EditorInfo.IME_ACTION_DONE);
        svProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()){ //Nếu searchText rỗng thì trả về toàn bộ
                    productArrayList.clear();
                    loadApi();
                }
                ArrayList<Product> list = new ArrayList<>();
                String strSearch = newText.toLowerCase().trim();
                for(Product product: productArrayList){
                    if(product.getName().toLowerCase().contains(strSearch)){
                        list.add(product);
                    }
                }
                productArrayList.clear();
                productArrayList.addAll(list);
                productAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    //Hàm gọi Api
    public void loadApi(){
        apiService = new ProductApiService();
        apiService.getProduct()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Product> products) {
                        for(Product product: products){
                            productArrayList.add(product);
                            productAdapter.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("DEBUGDEBUG","Fail" + e.getMessage());
                    }
                });
    }
}