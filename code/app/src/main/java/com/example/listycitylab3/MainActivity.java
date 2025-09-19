package com.example.listycitylab3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AddCityFragment.AddCityDialogListener, EditCityFragment.OnCityEditedListener {

    private ArrayList<City> dataList;
    private ListView cityListView;
    private CityArrayAdapter cityAdapter;

    @Override
    public void addCity(City city) {
        dataList.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCityEdited(int position, City editedCity) {
        if (position >= 0 && position < dataList.size()) {
            dataList.set(position, editedCity);
            cityAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {"Edmonton", "Vancouver", "Toronto"};
        String[] provinces = {"AB", "BC", "ON"};

        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityListView = findViewById(R.id.city_list_view);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityListView.setAdapter(cityAdapter);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) FloatingActionButton fab = findViewById(R.id.fab_add_city);
        fab.setOnClickListener(v -> {
            AddCityFragment.newInstance().show(getSupportFragmentManager(), "ADD_CITY_DIALOG");
        });

        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                City cityToEdit = dataList.get(position);
                EditCityFragment.newInstance(cityToEdit, position)
                        .show(getSupportFragmentManager(), "EDIT_CITY_DIALOG");
            }
        });
    }
}
