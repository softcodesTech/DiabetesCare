package com.example.diabetescare;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://softcodes.tech/diabetes/";

    @GET("fetch_data.php")
    Call<List<SugarLevelsPicked>> getstatus();


}
