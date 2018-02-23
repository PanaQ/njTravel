package travel.nanjing.com.travel.business.travelRecord.addRecord;

import android.view.View;
import android.widget.ImageView;

import com.handarui.iqfun.business.base.BaseViewModel;

/**
 * Created by zx on 2018/2/11 0011.
 */

public class AddRecordViewModel extends BaseViewModel<AddRecordActivity> {

    protected ImageView clickView;

    public AddRecordViewModel(AddRecordActivity view) {
        super(view);
    }

    public void addRecord(View view) {
        this.getView().finish();
    }

    public void addPicture(View view) {
        clickView = (ImageView) view;
        this.getView().startPicture();
    }
}
