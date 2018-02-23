package travel.nanjing.com.travel.business.funs;

import android.databinding.ObservableField;

import com.handarui.iqfun.business.base.BaseViewModel;

/**
 * Created by zx on 2018/2/22 0022.
 */

public class FriendsViewModel extends BaseViewModel<FriendsActivity> {

    public ObservableField<Integer> dataSize = new ObservableField<>(0);

    public FriendsViewModel(FriendsActivity view) {
        super(view);
    }

}
