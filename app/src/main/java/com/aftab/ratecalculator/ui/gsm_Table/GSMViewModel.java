package com.aftab.ratecalculator.ui.gsm_Table;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GSMViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GSMViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is GSM fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}