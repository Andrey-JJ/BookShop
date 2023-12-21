package ru.mivlgu.bookshop.models.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookingViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public LiveData<String> getText() {
        return mText;
    }

    public BookingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is booking fragment");
    }
}
