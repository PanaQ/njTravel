package travel.nanjing.com.travel.business.travelRecord.addRecord;

import android.view.View;

import com.handarui.iqfun.business.base.BaseViewModel;

/**
 * Created by zx on 2018/2/11 0011.
 */

public class AddRecordViewModel extends BaseViewModel<AddRecordActivity> {

    public AddRecordViewModel(AddRecordActivity view) {
        super(view);
    }

    public void addRecord(View view) {
        this.getView().finish();
    }
}
