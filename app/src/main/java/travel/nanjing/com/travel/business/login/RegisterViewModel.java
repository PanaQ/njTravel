package travel.nanjing.com.travel.business.login;

import android.content.Intent;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.handarui.iqfun.business.base.BaseViewModel;

import travel.nanjing.com.travel.R;
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
        if (sameState.get()) {
            this.getView().startActivity(new Intent(this.getView(), MainActivity.class));
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
//        RxUtil.wrapRestCall().subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                this.getView().startActivity(new Intent(this.getView(), MainActivity.class));
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//
//            }
//        });
    }
}
