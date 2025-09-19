package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {
    // These are the member variables for your EditTexts
    private EditText cityNameEditText;
    private EditText provinceNameEditText;
    private AddCityDialogListener listener;

    public interface AddCityDialogListener {
        void addCity(City city);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement AddCityDialogListener");
        }
    }

    public static AddCityFragment newInstance() {
        return new AddCityFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_add_city, null);

        // *** THIS IS THE CHANGED SECTION ***
        // It now uses the correct IDs from your fragment_add_city.xml
        cityNameEditText = view.findViewById(R.id.editText_city_name);
        provinceNameEditText = view.findViewById(R.id.editText_province_name);
        // *** END OF CHANGED SECTION ***

        builder.setView(view)
                .setTitle("Add City")
                .setPositiveButton("Add", (dialog, which) -> {
                    String cityName = "";
                    String provinceName = "";

                    // Null check before calling getText to prevent potential NullPointerException
                    // if findViewById somehow still failed (though it shouldn't after this fix)
                    if (cityNameEditText != null) {
                        cityName = cityNameEditText.getText().toString().trim();
                    }
                    if (provinceNameEditText != null) {
                        provinceName = provinceNameEditText.getText().toString().trim();
                    }

                    if (!cityName.isEmpty() && !provinceName.isEmpty() && listener != null) {
                        listener.addCity(new City(cityName, provinceName));
                    }
                })
                .setNegativeButton("Cancel", null);

        return builder.create();
    }
}

