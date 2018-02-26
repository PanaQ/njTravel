package travel.nanjing.com.travel.business.login;

import android.content.Intent;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseViewModel;
import com.zhexinit.ov.common.bean.RequestBean;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.api.bo.LoginBean;
import travel.nanjing.com.travel.api.service.UserLoginService;
import travel.nanjing.com.travel.business.MainActivity;

/**
 * Created by zx on 2018/2/22 0022.
 */

public class RegisterViewModel extends BaseViewModel<RegisterActivity> {

    public ObservableField<String> phoneNum = new ObservableField<>("");
    public ObservableField<String> passWord = new ObservableField<>("");
    public ObservableField<String> passWordConfirm = new ObservableField<>("");
    public ObservableField<Boolean> sameState = new ObservableField<>(false);

    public RegisterViewModel(RegisterActivity view) {
        super(view);
    }

    public void afterChange(Editable editable) {
        if (passWord.get().equals(passWordConfirm.get())) {
            sameState.set(true);
        } else {
            sameState.set(false);
        }
    }

    public void commit(View view) {
        if (sameState.get() && !TextUtils.isEmpty(phoneNum.get()) && !TextUtils.isEmpty(passWord.get())) {
            requestRegiste();
        }
    }

    public void showPass(View view) {
        pwdControl(this.getView().pwdFirstEt, ((ImageView) view));
    }

    public void showPassConfirm(View view) {
        pwdControl(this.getView().pwdAgainEt, ((ImageView) view));
    }

    public void pwdControl(EditText editText, ImageView pwdControl) {
        if (!editText.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
            //设置EditText文本为可见的
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            pwdControl.setImageResource(R.mipmap.ic_open_eye);
        } else {
            //设置EditText文本为隐藏的
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            pwdControl.setImageResource(R.mipmap.ic_close_eye);
        }
        editText.postInvalidate();
        //切换后将EditText光标置于末尾
        CharSequence charSequence = editText.getText();
        if (charSequence != null) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
    }

    void requestRegiste() {
        RequestBean<LoginBean> requestBean = RequestBeanMaker.getRequestBean();

        LoginBean param = new LoginBean();
        param.setLogin(phoneNum.get());
        param.setPassword(passWord.get());
        requestBean.setParam(param);


        UserLoginService service = RetrofitFactory.createRestService(UserLoginService.class);
        RxUtil.wrapRestCall(service.register(requestBean), requestBean.getReqId()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                getView().startActivity(new Intent(getView(), MainActivity.class));
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i(TAG, throwable.getMessage());
            }
        });
    }

    private static final String TAG = "RegisterViewModel";
}
