package travel.nanjing.com.travel.business.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.handarui.iqfun.business.base.BaseVMActivity;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.databinding.ActivityLoginBinding;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseVMActivity<LoginActivity, LoginViewModel> {

    private EditText count;
    private EditText password;
    private ActivityLoginBinding dataBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        dataBinding.setViewModel(viewModel);

    }

    @Override
    public void initViewModel() {
        viewModel = new LoginViewModel(this);
    }
}

