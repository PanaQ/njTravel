package travel.nanjing.com.travel.business.together;

import android.content.Intent;
import android.view.View;

import com.handarui.iqfun.business.base.BaseViewModel;

import travel.nanjing.com.travel.business.together.addTogether.AddTogetherActivity;

/**
 * Created by zx on 2018/2/7 0007.
 */

public class TogetherViewModel extends BaseViewModel<TogetherFragment> {
    public TogetherViewModel(TogetherFragment view) {
        super(view);
    }

    void addRecord(View view) {
        this.getView().getContext().startActivity(new Intent(this.getView().getContext(), AddTogetherActivity.class));
    }
}
