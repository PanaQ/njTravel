package travel.nanjing.com.travel.business.together.addTogether;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.handarui.iqfun.business.base.BaseVMActivity;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.databinding.ActivityAddTogetherBinding;

public class AddTogetherActivity extends BaseVMActivity<AddTogetherActivity, AddTogetherViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddTogetherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_together);
        binding.setViewModel(this.viewModel);
    }

    @Override
    public void initViewModel() {
        this.viewModel = new AddTogetherViewModel(this);
    }
}
