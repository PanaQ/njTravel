package travel.nanjing.com.travel.business.together.addTogether;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.handarui.iqfun.business.base.BaseViewModel;

/**
 * Created by zx on 2018/2/11 0011.
 */

public class AddTogetherViewModel extends BaseViewModel<AddTogetherActivity> {

    public ObservableField<String> title = new ObservableField<String>("");
    public ObservableField<String> content = new ObservableField<String>("");
    public ObservableField<Boolean> buttonEnabled=new ObservableField<Boolean>(false);

    public AddTogetherViewModel(AddTogetherActivity view) {
        super(view);
    }

    public void commit(View view) {
        this.getView().finish();
    }

    public void textChange(Editable editable) {
        if (TextUtils.isEmpty(title.get()) || TextUtils.isEmpty(content.get())) {
            buttonEnabled.set(false);
        } else {
            buttonEnabled.set(true);
        }
    }
}
