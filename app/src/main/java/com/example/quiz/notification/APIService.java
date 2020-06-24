package com.example.quiz.notification;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAhwGyY6U:APA91bFNLmgPPztEG9vKUXkxcmURegSZwd-w7v2mSnIQ1-t-EGuJyPkhDOpk0CIE6dpJ68x3oIJpsM9uycMzNd93mrr_rmIUBhxPCCFuAeVehXw_ZSQcdGzaHpQv7mU0BrjVm2aQv0Xa"
    })

    @POST("fcm/send")
    Call<Response> sendNotification(@Body Sender body);


}
