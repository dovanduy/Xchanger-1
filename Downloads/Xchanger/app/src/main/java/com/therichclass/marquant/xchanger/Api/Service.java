package com.therichclass.marquant.xchanger.Api;
import  com.therichclass.marquant.xchanger.Api.Url;
import com.therichclass.marquant.xchanger.Utils.CurrencyExchange;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by marquant on 11/2/2017.
 */

public interface Service {
    @GET(Url.SEARCH_URL)
    Call<CurrencyExchange> loadCurrencyExchange();
}
