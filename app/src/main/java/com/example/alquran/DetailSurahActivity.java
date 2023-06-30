package com.example.alquran;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquran.model.AyatModel.Verses;
import com.example.alquran.model.AyatModel.VersesItem;
import com.example.alquran.model.Terjemahan.Indo;
import com.example.alquran.model.Terjemahan.TranslationsItem;
import com.example.alquran.retrofit.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSurahActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterAyats adapterAyats;
    private final List<VersesItem> versesItemList = new ArrayList<>();
    private final List<TranslationsItem> translationsItemList = new ArrayList<>();
    List<VersesItem> ayat;
    List<TranslationsItem> arti;
    TextView NameComplexSurah, JumlahAyat, Tempat, noSurah, Arabic;
    Button BTAudio;
    private MediaPlayer mediaPlayer;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aktivity_detail_surah);

        int id = getIntent().getIntExtra("id", 1);
        noSurah = findViewById(R.id.NoSurah);
        noSurah.setText(String.valueOf(id));

        String nameComplex = getIntent().getStringExtra("name");
        NameComplexSurah = findViewById(R.id.Judul);
        NameComplexSurah.setText((nameComplex));

        String tempat = getIntent().getStringExtra("tempat");
        Tempat = findViewById(R.id.Tempat);
        Tempat.setText("Tempat Diturunkan " + tempat);

        int jumlahAyat = getIntent().getIntExtra("verses", 1);
        JumlahAyat = findViewById(R.id.JumlahAyat);
        JumlahAyat.setText("Jumlah Ayat " + jumlahAyat);

        mediaPlayer = new MediaPlayer();
        String audioUrl = getIntent().getStringExtra("audio_url");
        BTAudio = findViewById(R.id.BTAudio);
        BTAudio.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()){
                pauseAudio();
            } else {
                playAudio(audioUrl);
            }
        });

        setUpView();
        setUpRecyclerView();
        System.out.println(id);
        getDatafromApi(id);
    }
    private void pauseAudio() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }
    private void playAudio(String audio) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(audio);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void getDatafromApi(int id) {
        ApiService.endPoint().getIndo(id).enqueue(new Callback<Indo>() {
            @Override
            public void onResponse(@NonNull Call<Indo> call, @NonNull Response<Indo> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    DetailSurahActivity.this.arti = response.body().getTranslations();
                    getVersesData(getIntent().getIntExtra("id", 1));
                }
            }
            @Override
            public void onFailure(@NonNull Call<Indo> call, @NonNull Throwable t) {
                Log.d("error", t.toString());
            }
        });
    }

    private void getVersesData(int id) {
        ApiService.endPoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(@NonNull Call<Verses> call, @NonNull Response<Verses> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    DetailSurahActivity.this.ayat = response.body().getVerses();
                    adapterAyats.setData(ayat, arti);
                }
            }
            @Override
            public void onFailure(@NonNull Call<Verses> call, @NonNull Throwable t) {
                Log.d("error", t.toString());
            }
        });
    }
    private void setUpRecyclerView() {
        adapterAyats = new AdapterAyats(versesItemList, translationsItemList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAyats);
    }
    private void setUpView() {
        recyclerView = findViewById(R.id.RVAyat);
    }

}