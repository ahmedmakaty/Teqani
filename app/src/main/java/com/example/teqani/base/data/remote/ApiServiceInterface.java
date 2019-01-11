package com.example.teqani.base.data.remote;

import com.example.teqani.base.data.model.RegisterResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServiceInterface {


    @POST("users/customerSignUp")
    Flowable<RegisterResponse> register(@HeaderMap Map<String, String> headers, @Body HashMap<String, String> body);

    @POST("users/oneTimePassword")
    Completable verify(@HeaderMap Map<String, String> headers, @Body HashMap<String, String> body);

    @POST("users/login")
    Completable login(@HeaderMap Map<String, String> headers, @Body HashMap<String, String> body);

    @POST("users/oneTimePassword")
    Completable generateOTP(@HeaderMap Map<String, String> headers, @Body HashMap<String, String> body);

    @GET("PaymentMethod/GetPaymentMethods")
    Flowable<String> getPaymentMethods(@HeaderMap Map<String, String> headers, @Query("countryid") String countryId, @Query("languageId") String languageId);
}
