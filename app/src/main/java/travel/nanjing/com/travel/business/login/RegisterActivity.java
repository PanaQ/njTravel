package travel.nanjing.com.travel.business.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.EditText;

import com.handarui.iqfun.business.base.BaseVMActivity;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseVMActivity<RegisterActivity, RegisterViewModel> {

    private ActivityRegisterBinding dataBinding;
    protected EditText pwdFirstEt;
    protected EditText pwdAgainEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        dataBinding.setViewModel(this.viewModel);
        pwdFirstEt = dataBinding.pwdFirstEt;
        pwdAgainEt = dataBinding.pwdAgainEt;

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    @Override
    public void initViewModel() {
        this.viewModel = new RegisterViewModel(this);
    }
}
