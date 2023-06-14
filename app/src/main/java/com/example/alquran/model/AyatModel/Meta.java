package com.example.alquran.model.AyatModel;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("filters")
    private Fillters filters;

    public Fillters getFilters(){
        return filters;
    }

    @Override
    public String toString(){
        return
                "Meta{" +
                        "filters = '" + filters + '\'' +
                        "}";
    }
}
