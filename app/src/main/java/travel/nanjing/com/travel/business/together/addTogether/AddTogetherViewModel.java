package travel.nanjing.com.travel.business.together.addTogether;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.handarui.baselib.exception.SuccessException;
import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseViewModel;
import com.zhexinit.ov.common.bean.RequestBean;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.business.api.model.bo.AddMateNoteBo;
import travel.nanjing.com.travel.business.api.service.MateNoteService;

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
        param.setContent(content.get());
        param.setTitle(title.get());
        requestBean.setParam(param);

        MateNoteService service = RetrofitFactory.createRestService(MateNoteService.class);
        RxUtil.wrapRestCall(service.addMateNote(requestBean), requestBean.getReqId())
                .subscribe(new Consumer<Void>() {
                    @Override
                    public void accept(Void aVoid) throws Exception {
                        getView().finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable instanceof SuccessException) {
                            Toast.makeText(getView(), "发布成功", Toast.LENGTH_LONG).show();
                            getView().finish();
                        } else {
                            Toast.makeText(getView(), "发布失败，稍后再试", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "accept: " + throwable.getMessage());
                        }
                    }
                });
    }

    private static final String TAG = "AddTogetherViewModel";

    public void textChange(Editable editable) {
        if (TextUtils.isEmpty(title.get()) || TextUtils.isEmpty(content.get())) {
            buttonEnabled.set(false);
        } else {
            buttonEnabled.set(true);
        }
    }
}
