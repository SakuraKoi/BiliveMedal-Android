package sakura.kooi.android.bilivemedal.fragments.rank;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RankViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RankViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}