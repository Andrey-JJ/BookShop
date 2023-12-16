package ru.mivlgu.bookshop.models.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PublisherViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public PublisherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is publisher fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
