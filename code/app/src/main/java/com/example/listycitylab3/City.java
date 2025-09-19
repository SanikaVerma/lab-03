package com.example.listycitylab3;

import java.io.Serializable;
import java.util.Objects;

public class City implements Serializable {
    private String cityName;
    private String provinceName;

    public City(String cityName, String provinceName) {
        this.cityName = cityName;
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(cityName, city.cityName) &&
                Objects.equals(provinceName, city.provinceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, provinceName);
    }
}
