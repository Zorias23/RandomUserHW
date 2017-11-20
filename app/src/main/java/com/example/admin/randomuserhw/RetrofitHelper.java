package com.example.admin.randomuserhw;

import com.example.admin.randomuserhw.model.RandomUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by singh on 11/16/17.
 */

public class RetrofitHelper {

    public static final String BASE_URL = "https://randomuser.me/";

    //    build the retrofit object to be used
    public static Retrofit create() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }

    //call the interface to get the response
    public static Call<RandomUser> getRandomUser(){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRandomUser();
    }

    public static Call<RandomUser> getRandomUsers(){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRandomUsers();
    }
    public static Call<RandomUser> getRandomMaleUsers(){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRandomMaleUsers();
    }
    public static Call<RandomUser> getRandomFemaleUsers(){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRandomFemaleUsers();
    }
    /*
    public static Call<List<GithubRepository>> getMyRepositories(String user){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRepositories(user);
    } */


    //    create an interface for http verbs
    interface RetrofitService {

        @GET("api/")
        Call<RandomUser> getRandomUser();

        @GET("api/?results=10")
        Call<RandomUser> getRandomUsers();

        @GET("api/?results=10&gender=male")
        Call<RandomUser> getRandomMaleUsers();

        @GET("api/?results=10&gender=female")
        Call<RandomUser> getRandomFemaleUsers();
       // @GET("users/{user}/repos")
       // Call<List<GithubRepository>> getRepositories(@Path("user") String user);
    }


}
