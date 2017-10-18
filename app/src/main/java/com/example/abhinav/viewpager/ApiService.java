package com.example.abhinav.viewpager;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by abhinav on 18/10/17.
 */

public interface ApiService {
    @GET("/json_data.json")
    Call<ContactList> getMyJSON();
}
