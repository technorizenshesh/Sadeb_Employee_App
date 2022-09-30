package com.codeinger.sadeb_employee_app.network.request;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestAuthentication {

    @POST("provider_user_login")
    @FormUrlEncoded
    Call<JsonElement> getlogIn(@Field("email") String email,
                               @Field("password") String password);

    @POST("get_provideruser_request_booking")
    @FormUrlEncoded
    Call<JsonElement> getBookingAppointment(@Field("provider_user_id") String provider_user_id);


    @POST("provider_accept_and_Cancel_request")
    @FormUrlEncoded
    Call<JsonElement> makestatus(@Field("status") String status,
                                 @Field("request_id") String request);
}
