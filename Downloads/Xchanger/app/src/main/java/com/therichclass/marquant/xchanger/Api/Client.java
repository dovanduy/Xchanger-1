package com.therichclass.marquant.xchanger.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.therichclass.marquant.xchanger.Api.Url;

/**
 * Created by marquant on 11/2/2017.
 */

public class Client {
    private static Retrofit retrofit = null;

    //The get client method that returns a retrofit obj is created
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Url.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
