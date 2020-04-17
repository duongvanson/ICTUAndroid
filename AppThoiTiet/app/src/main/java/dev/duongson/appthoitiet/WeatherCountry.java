package dev.duongson.appthoitiet;

import java.io.Serializable;

public class WeatherCountry implements Serializable {
    String nameCountry;
    int temp;

    public WeatherCountry(String nameCountry, int temp) {
        this.nameCountry = nameCountry;
        this.temp = temp;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }
}
