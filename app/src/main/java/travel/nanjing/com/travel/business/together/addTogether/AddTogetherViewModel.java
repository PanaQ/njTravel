package travel.nanjing.com.travel.business.together.addTogether;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseViewModel;
import com.zhexinit.ov.common.bean.RequestBean;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.api.bo.AddMateNoteBo;
import travel.nanjing.com.travel.api.bo.BaseNoteBo;
import travel.nanjing.com.travel.api.bo.MateNoteBo;
import travel.nanjing.com.travel.api.service.MateNoteService;


/**
 * Created by zx on 2018/2/11 0011.
 */

public class AddTogetherViewModel extends BaseViewModel<AddTogetherActivity> {

    public ObservableField<String> title = new ObservableField<String>("");
    public ObservableField<String> content = new ObservableField<String>("");
    public ObservableField<Boolean> buttonEnabled = new ObservableField<Boolean>(false);

    public AddTogetherViewModel(AddTogetherActivity view) {
        super(view);
    }

    public void commit(View view) {
        RequestBean<AddMateNoteBo> requestBean = RequestBeanMaker.getRequestBean();
        AddMateNoteBo param = new AddMateNoteBo();
        param.setTitle(this.getView().binding.title.getText().toString());
        param.setContent(this.getView().binding.content.getText().toString());
        requestBean.setParam(param);
        MateNoteService restService = RetrofitFactory.createRestService(MateNoteService.class);
        RxUtil.wrapRestCall(restService.addMateNote(requestBean), requestBean.getReqId()).subscribe(new Consumer<Void>() {
            @Override
            public void accept(Void aVoid) throws Exception {
                getView().finish();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept: " + throwable.getMessage());
            }
        });
    }

    public void textChange(Editable editable) {
        if (TextUtils.isEmpty(title.get()) || TextUtils.isEmpty(content.get())) {
            buttonEnabled.set(false);
        } else {
            buttonEnabled.set(true);
        }
    }

    private static final String TAG = "AddTogetherViewModel";
}
