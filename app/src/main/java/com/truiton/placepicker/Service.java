package com.truiton.placepicker;



import com.truiton.placepicker.models.Avaliacao;
import com.truiton.placepicker.models.CatalagoAvaliacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by elvis on 10/08/17.
 */

public interface Service {

    public static final String BASE_URL = "http://192.168.1.106:8080/WebServiceTrabalho/mkt/";

    @GET("avaliacao/get/{id}")
    Call<List<Avaliacao>> getAvaliacao(@Path("id") int id);
}
