package travel.nanjing.com.travel.business.travelRecord;

import android.content.Intent;
import android.view.View;

import com.handarui.iqfun.business.base.BaseViewModel;

import travel.nanjing.com.travel.business.travelRecord.addRecord.AddRecordActivity;

/**
 * Created by zx on 2018/2/7 0007.
 */

public class TravelRecordViewModel extends BaseViewModel<TravelRecordFragment> {

    private static final String TAG = "TravelRecordViewModel";

    public TravelRecordViewModel(TravelRecordFragment view) {
        super(view);
    }

    public void addRecord(View view) {
        this.getView().getContext().startActivity(new Intent(this.getView().getContext(), AddRecordActivity.class));
    }
}
