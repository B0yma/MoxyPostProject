package com.boyma.mxsmpl.net.jsonplaceholder.allnews;


import com.boyma.mxsmpl.net.jsonplaceholder.allnews.models.ResponseJsonObj;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonPlaceHolderRepo {
    public Observable getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ISiteApi siteapi = retrofit.create(ISiteApi.class);

        Observable<List<ResponseJsonObj>> list = siteapi.getResponseJsonObj("/posts");
        return list;
    }
}
