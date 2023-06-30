package com.example.alquran.retrofit;

import android.provider.MediaStore;

import com.example.alquran.model.AudioModel.Audio;
import com.example.alquran.model.SurahModel.Chapters;
import com.example.alquran.model.AyatModel.Verses;
import com.example.alquran.model.Terjemahan.Indo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndPoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();

    @GET("chapter_recitations/33?")
    Call<Audio> getAudio();

    @GET("quran/verses/uthmani")
    Call<Verses> getAyat(@Query("chapter_number") int id);

    @GET("quran/translations/33?")
    Call<Indo> getIndo (@Query("chapter_number") int id);
}
