package com.aftab.ratecalculator.ui.gsm_Table;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.aftab.ratecalculator.databinding.FragmentGsmTableBinding;

public class GsmTableFragment extends Fragment {

    private FragmentGsmTableBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GSMViewModel slideshowViewModel =
                new ViewModelProvider(this).get(GSMViewModel.class);

        binding = FragmentGsmTableBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textGsmTable;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}