package com.example.task2_perkanttech.retrofit;

import com.example.task2_perkanttech.helpers.Constants;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET(Constants.URL_ENDPOINT)
    Call<ResponseBody> scoreBoardApi();
}
