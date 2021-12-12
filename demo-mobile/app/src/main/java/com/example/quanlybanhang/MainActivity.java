package com.example.quanlybanhang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quanlybanhang.data.model.Product;
import com.example.quanlybanhang.view.ListFragment;
import com.example.quanlybanhang.view.LoginFragment;
import com.example.quanlybanhang.view.OrderFragment;
import com.example.quanlybanhang.view.ProfileFragment;
import com.example.quanlybanhang.viewmodel.ProductApiService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bottom_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()){
            case R.id.list_fragment:
                fragment = new ListFragment();
                loadFragment(fragment);
                return true;
            case R.id.order_fragment:
                fragment = new OrderFragment();
                loadFragment(fragment);
                return true;
            case R.id.profile_fragment:
                fragment = new ProfileFragment();
                loadFragment(fragment);
                return true;
            case R.id.menu_logout:
                fragment = new LoginFragment();
                loadFragment(fragment);
                Toast.makeText(MainActivity.this,"Đã đăng xuất!", Toast.LENGTH_LONG).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}