package travel.nanjing.com.travel.business.login;

import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseViewModel;
import com.zhexinit.ov.common.bean.RequestBean;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.api.bo.LoginBean;
import travel.nanjing.com.travel.api.service.UserService;
import travel.nanjing.com.travel.business.MainActivity;

/**
 * Created by zx on 2018/2/22 0022.
 */

public class LoginViewModel extends BaseViewModel<LoginActivity> {

    public ObservableField<String> passWord = new ObservableField<>("");
    public ObservableField<String> count = new ObservableField<>("");

    public LoginViewModel(LoginActivity view) {
        super(view);
    }

    public void login(View view) {

        if (!TextUtils.isEmpty(count.get()) && !TextUtils.isEmpty(passWord.get())) {
            request();
        } else {
            Toast.makeText(this.getView(), "账号密码为空", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        this.getView().startActivity(new Intent(this.getView(), RegisterActivity.class));

    }

    private void request() {
        RequestBean<LoginBean> requestBean = RequestBeanMaker.getRequestBean();

        LoginBean param = new LoginBean();
        param.setLogin(count.get());
        param.setPassword(passWord.get());
        requestBean.setParam(param);

        RxUtil.wrapRestCall(RetrofitFactory.createRestService(UserService.class).login(requestBean), requestBean.getReqId())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        getView().startActivity(new Intent(getView(), MainActivity.class));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: " + throwable.getMessage());
                    }
                });
    }

    private static final String TAG = "LoginViewModel";
}
