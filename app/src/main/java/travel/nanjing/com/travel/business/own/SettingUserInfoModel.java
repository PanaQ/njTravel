package travel.nanjing.com.travel.business.own;

import android.content.Intent;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.handarui.baselib.exception.SuccessException;
import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseViewModel;
import com.handarui.iqfun.util.LoginUtils;
import com.squareup.picasso.Picasso;
import com.zhexinit.ov.common.bean.RequestBean;

import java.io.File;

import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.MainActivity;
import travel.nanjing.com.travel.business.api.model.bo.UserBo;
import travel.nanjing.com.travel.business.api.service.NoteService;
import travel.nanjing.com.travel.business.api.service.UserService;
import travel.nanjing.com.travel.util.RxUtils;

/**
 * Created by zx on 2018/2/7 0007.
 */

public class SettingUserInfoModel extends BaseViewModel<SettingUserInfoActivity> {

    private static final String TAG = "SettingUserInfoModel";

    public ObservableField<String> useCount = new ObservableField<>("");
    public ObservableField<String> userName = new ObservableField<>("");

    public SettingUserInfoModel(SettingUserInfoActivity view) {
        super(view);
    }

    public void submmit(View view) {

        File fileName = this.getView().fileName;

        if (fileName == null || TextUtils.isEmpty(useCount.get()) || TextUtils.isEmpty(userName.get())
                || !(this.getView().binding.boyRb.isChecked() || this.getView().binding.girlRb.isChecked())
                ) {
            Toast.makeText(this.getView(), "请完善用户信息", Toast.LENGTH_LONG).show();
            return;
        }

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileName);
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", fileName.getName(), requestFile);

        String descriptionString = "picture";

        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        RxUtils.wrapRestCall(RetrofitFactory.createRestService(NoteService.class)
                .uploadPicture(body, description))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        requestUpdateUserInfo(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getView(),throwable.getMessage(),Toast.LENGTH_LONG).show();
                        Log.e(TAG, "accept: " + throwable.getMessage());
                    }
                });

    }

    public void requestUpdateUserInfo(String s) {
        RequestBean<UserBo> requestBean = RequestBeanMaker.getRequestBean();
        final UserBo param = new UserBo();
        param.setName(LoginUtils.INSTANCE.getName());
        param.setPhone(useCount.get());
        param.setEmail(userName.get());
        param.setId(LoginUtils.INSTANCE.getId());
        param.setAvatar(s);
        param.setGender(1);
        requestBean.setParam(param);

        RxUtil.wrapRestCall(RetrofitFactory.createRestService(UserService.class).updateMyInfo(requestBean),requestBean.getReqId())
                .subscribe(new Consumer<Void>() {
                    @Override
                    public void accept(Void o) throws Exception {
                        LoginUtils.INSTANCE.saveUserInfo(param);
                        getView().startActivity(new Intent(getView(), MainActivity.class));
                        getView().finish();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable instanceof SuccessException) {
                            LoginUtils.INSTANCE.saveUserInfo(param);
                            getView().startActivity(new Intent(getView(), MainActivity.class));
                            getView().finish();
                        }else {
                            Toast.makeText(getView(),throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void init() {
        if (!TextUtils.isEmpty(LoginUtils.INSTANCE.getPortraitUrl())) {
            Picasso.with(this.getView()).load(LoginUtils.INSTANCE.getAva());
        }
        if (LoginUtils.INSTANCE.getSex() != -1) {
            if (LoginUtils.INSTANCE.getSex() == 0) {
                getView().binding.sexGroup.check(R.id.boyRb);
            } else {
                getView().binding.sexGroup.check(R.id.girlRb);
            }
        }
        if (!TextUtils.isEmpty(LoginUtils.INSTANCE.getPhoneNum())) {
            useCount.set(LoginUtils.INSTANCE.getPhoneNum());
        }
        if (!TextUtils.isEmpty(LoginUtils.INSTANCE.getName())) {
            useCount.set(LoginUtils.INSTANCE.getPhoneNum());
        }
    }
}
