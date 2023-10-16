package com.vnshk.smallcase.api

import com.vnshk.smallcase.datamodels.DummyDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers("Accept: Application/json")
    @GET("todos/1")
    fun fetchData() : Call<DummyDataModel?>?
}