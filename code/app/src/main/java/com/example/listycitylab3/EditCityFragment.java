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


public class EditCityFragment extends DialogFragment {

    private static final String ARG_CITY_TO_EDIT = "city_to_edit";
    private static final String ARG_CITY_POSITION = "city_position";

    private EditText cityNameEditTextInEdit;
    private EditText provinceNameEditTextInEdit;
    private City cityToEdit;
    private int cityPosition;
    private OnCityEditedListener listener;

    public interface OnCityEditedListener {
        void onCityEdited(int position, City editedCity);
    }

    public static EditCityFragment newInstance(City city, int position) {
        EditCityFragment fragment = new EditCityFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY_TO_EDIT, city);
        args.putInt(ARG_CITY_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCityEditedListener) {
            listener = (OnCityEditedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnCityEditedListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_edit_city, null);

        cityNameEditTextInEdit = view.findViewById(R.id.edit_text_city_name_edit_frag);
        provinceNameEditTextInEdit = view.findViewById(R.id.edit_text_province_name_edit_frag);

        if (getArguments() != null) {
            cityToEdit = (City) getArguments().getSerializable(ARG_CITY_TO_EDIT);
            cityPosition = getArguments().getInt(ARG_CITY_POSITION);
            if (cityToEdit != null) {
                cityNameEditTextInEdit.setText(cityToEdit.getCityName());
                provinceNameEditTextInEdit.setText(cityToEdit.getProvinceName());
            }
        }

        builder.setView(view)
                .setTitle("Edit City")
                .setPositiveButton("Save", (dialog, which) -> {
                    String updatedCityName = cityNameEditTextInEdit.getText().toString().trim();
                    String updatedProvinceName = provinceNameEditTextInEdit.getText().toString().trim();
                    if (!updatedCityName.isEmpty() && !updatedProvinceName.isEmpty() && listener != null && cityToEdit != null) {
                        City editedCity = new City(updatedCityName, updatedProvinceName);
                        listener.onCityEdited(cityPosition, editedCity);
                    }
                })
                .setNegativeButton("Cancel", null);

        return builder.create();
    }
}
