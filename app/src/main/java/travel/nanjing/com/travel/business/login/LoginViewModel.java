package travel.nanjing.com.travel.business.login;

import android.content.Intent;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.handarui.iqfun.business.base.BaseViewModel;

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
        if ("root".equals(count.get()) &&
                "root".equals(passWord.get())) {
            this.getView().startActivity(new Intent(this.getView(), MainActivity.class));
        } else {
            Toast.makeText(this.getView(), "账号密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view) {
        this.getView().startActivity(new Intent(this.getView(), RegisterActivity.class));

    }

    private void request() {

//        RxUtil.wrapRestCall().subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                 this.getView().startActivity(new Intent(this.getView(), MainActivity.class));
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//
//            }
//        });
    }
}
