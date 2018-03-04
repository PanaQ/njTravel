package travel.nanjing.com.travel.business.travelRecord.addRecord;

import android.view.View;
import android.widget.ImageView;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseViewModel;
import com.zhexinit.ov.common.bean.RequestBean;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.api.bo.BaseNoteBo;
import travel.nanjing.com.travel.api.service.NoteService;

/**
 * Created by zx on 2018/2/11 0011.
 */

public class AddRecordViewModel extends BaseViewModel<AddRecordActivity> {

    protected ImageView clickView;

    public AddRecordViewModel(AddRecordActivity view) {
        super(view);
    }

    public void addRecord(View view) {
        RequestBean<BaseNoteBo> requestBean = RequestBeanMaker.getRequestBean();
        BaseNoteBo param = new BaseNoteBo();
        param.setTitle(this.getView().binding.title.getText().toString());
        param.setContent(this.getView().binding.content.getText().toString());
        requestBean.setParam(param);
        NoteService restService = RetrofitFactory.createRestService(NoteService.class);
        RxUtil.wrapRestCall(restService.addNote(requestBean), requestBean.getReqId()).subscribe(new Consumer<Void>() {
            @Override
            public void accept(Void aVoid) throws Exception {
                getView().finish();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    public void addPicture(View view) {
        clickView = (ImageView) view;
        this.getView().startPicture();
    }
}
