package ru.mivlgu.bookshop.models.view;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AuthorViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public LiveData<String> getText() {
        return mText;
    }

    public AuthorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is author fragment");
    }
}
