package com.example.alquran.retrofit;

import com.example.alquran.model.SurahModel.Chapters;
import com.example.alquran.model.AyatModel.Verses;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();

    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);
}
