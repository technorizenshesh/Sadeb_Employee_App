package com.codeinger.sadeb_employee_app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.codeinger.sadeb_employee_app.R;
import com.codeinger.sadeb_employee_app.adapter.AllServiceReqAdapter;
import com.codeinger.sadeb_employee_app.databinding.ActivityMainBinding;
import com.codeinger.sadeb_employee_app.network.NetworkConstraint;
import com.codeinger.sadeb_employee_app.network.RetrofitClient;
import com.codeinger.sadeb_employee_app.network.model.ResponseAuthError;
import com.codeinger.sadeb_employee_app.network.model.ResponseAuthenticationResponse;
import com.codeinger.sadeb_employee_app.network.model.StatusResponse;
import com.codeinger.sadeb_employee_app.network.model.userreq.AllUserRequestResponse;
import com.codeinger.sadeb_employee_app.network.model.userreq.ResultItem;
import com.codeinger.sadeb_employee_app.network.request.RequestAuthentication;
import com.codeinger.sadeb_employee_app.utils.SharedPrefsManager;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private int API_COUNT;
    private final int ALL_API_COUNT = 1;
    private final List<ResultItem> list = new ArrayList<>();
    private ActivityMainBinding binding;
    private AllServiceReqAdapter adapter;
    private AllUserRequestResponse authentication;
    private int user_id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    private void init() {

        getBookingRequest();
        binding.ivLogout.setOnClickListener(v -> {
            SharedPrefsManager.getInstance().clearPrefs();
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            finish();
        });

        binding.recyclerReequuest.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AllServiceReqAdapter(MainActivity.this,list, new AllServiceReqAdapter.Status() {
            @Override
            public void acceptcontrol(int position, DoneCallback callback) {
                acceptResponse(position,callback);
            }
            @Override
            public void declinetcontrol(int position, DoneCallback callback) {
                declineResponce(position,callback);
            }
            @Override
            public void completecontrol(int position, DoneCallback callback) {
                completeResponce(position,callback);
            }
        });
        binding.recyclerReequuest.setAdapter(adapter);
    }

    private void getBookingRequest() {
        Log.i("cscc", "getBookingRequest: " + 12);
        ResponseAuthenticationResponse model = SharedPrefsManager.getInstance().getObject("employee", ResponseAuthenticationResponse.class);
        binding.llMain.setVisibility(View.GONE);
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);
        if (model != null) {
            Log.i("adcscscsc", "getBookingRequest: "+model.getResult().getUserId());
            Log.i("adcscscsc", "acfafa: "+model.getResult().toString());
            String User_ID = model.getResult().getId();
            RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                    .create(RequestAuthentication.class)
                    .getBookingAppointment(User_ID)
                    .enqueue(new Callback<JsonElement>() {
                        @Override
                        public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                            ++API_COUNT;
                            if (API_COUNT == ALL_API_COUNT) {
                                binding.llMain.setVisibility(View.VISIBLE);
                                binding.loaderLayout.loader.setVisibility(View.GONE);
                            }
                            Log.i("scvdbx", "onResponse: " + response.toString());
                            if (response != null) {
                                if (response.isSuccessful()) {
                                    JsonObject object = response.body().getAsJsonObject();
                                    int status = object.get("status").getAsInt();
                                    Log.i("scvdbx", "sta: " + status);
                                    if (status == 1) {
                                          authentication = new Gson().fromJson(object, AllUserRequestResponse.class);
                                        list.addAll(authentication.getResult());
                                        adapter.notifyDataSetChanged();
                                        Log.i("dvdvvxv", "onResponse: " + list.toString());
                                    } else {
                                        ResponseAuthError authentication = new Gson().fromJson(object, ResponseAuthError.class);
                                        Toast.makeText(MainActivity.this, authentication.getResult(), Toast.LENGTH_SHORT).show();
                                        if (list.size() == 0) {
                                            binding.tvNoProductFound.setVisibility(View.VISIBLE);
                                        }
                                    }
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<JsonElement> call, Throwable t) {
                            ++API_COUNT;
                            if (API_COUNT == ALL_API_COUNT) {
                                Log.i("scvdbx", "sta: " + t.getMessage());
                            }
                        }
                    });
        }
    }

    private void completeResponce(int position, DoneCallback callback) {
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(RequestAuthentication.class)
                .makestatus("Complete", authentication.getResult().get(position).getId()
                )
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                        if (response != null) {
                            if (response.isSuccessful()) {
                                JsonObject object = response.body().getAsJsonObject();
                                int status = object.get("status").getAsInt();
                                if (status == 1) {
                                    callback.done();
                                    StatusResponse statusResponse = new Gson().fromJson(object, StatusResponse.class);
                                    Log.i("scdvddvv", "onResponse: " + statusResponse.getResult());
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                    }
                });
    }
    private void declineResponce(int position, DoneCallback callback) {
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);

        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(RequestAuthentication.class)
                .makestatus("Cancel", authentication.getResult().get(position).getId()
                )
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                        binding.loaderLayout.loader.setVisibility(View.GONE);

                        if (response != null) {
                            if (response.isSuccessful()) {
                                JsonObject object = response.body().getAsJsonObject();
                                int status = object.get("status").getAsInt();
                                if (status == 1) {
                                    callback.done();
                                    StatusResponse statusResponse = new Gson().fromJson(object, StatusResponse.class);
                                    Log.i("scdvddvv", "onResponse: " + statusResponse.getResult());
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);
                    }
                });
    }
    private void acceptResponse(int position, DoneCallback callback) {
        binding.loaderLayout.loader.setVisibility(View.VISIBLE);
        RetrofitClient.getClient(NetworkConstraint.BASE_URL)
                .create(RequestAuthentication.class)
                .makestatus("Accept", authentication.getResult().get(position).getId()
                        )
                .enqueue(new Callback<JsonElement>() {
                    @Override
                    public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                        binding.loaderLayout.loader.setVisibility(View.GONE);

                        Log.i("scvdbx", "onResponse: " + response.toString());

                        if (response != null) {
                            if (response.isSuccessful()) {
                                JsonObject object = response.body().getAsJsonObject();
                                int status = object.get("status").getAsInt();
                                if (status == 1) {
                                    callback.done();
                                    StatusResponse statusResponse = new Gson().fromJson(object, StatusResponse.class);
                                    Log.i("scdvddvv", "onResponse: " + statusResponse.getResult());
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonElement> call, Throwable t) {
                        binding.loaderLayout.loader.setVisibility(View.GONE);


                        Log.i("cxvxv", "onFailure: " + t.getMessage());
                    }
                });

    }


    public interface DoneCallback {
        void done();
    }
}