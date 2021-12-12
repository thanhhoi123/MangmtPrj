package com.example.quanlybanhang.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlybanhang.R;
import com.example.quanlybanhang.data.model.CurrentUser;
import com.example.quanlybanhang.data.model.ListApi;
import com.example.quanlybanhang.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText edtUsername = view.findViewById(R.id.edt_login_username);
        EditText edtPassword = view.findViewById(R.id.edt_login_password);
        TextView tvStatus = view.findViewById(R.id.tv_login_status);
        Button btnLogin = view.findViewById(R.id.btn_login_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://quanlybanhangapi.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ListApi listApi = retrofit.create(ListApi.class);
                User user = new User(1, null, edtUsername.getText() + "", edtPassword.getText() + "", 0, null, null, null);
                Call<User> call = listApi.createPost(user);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User responseFromAPI = response.body();
                        if(responseFromAPI.getName() != null){
                            CurrentUser.mUser = responseFromAPI;
                            ListFragment listFragment = new ListFragment();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, listFragment).commit();
                            Toast.makeText(getActivity(),"Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            tvStatus.setText("Sai username hoặc password!");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("DEBUGAPI", "Error: " + t.getMessage());
                    }
                });
            }
        });
    }

}