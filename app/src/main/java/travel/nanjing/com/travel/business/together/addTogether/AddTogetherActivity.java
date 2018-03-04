package travel.nanjing.com.travel.business.together.addTogether;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.handarui.iqfun.business.base.BaseVMActivity;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.databinding.ActivityAddTogetherBinding;

public class AddTogetherActivity extends BaseVMActivity<AddTogetherActivity, AddTogetherViewModel> {
    protected ActivityAddTogetherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         binding = DataBindingUtil.setContentView(this, R.layout.activity_add_together);
        binding.setViewModel(this.viewModel);
    }

    @Override
    public void initViewModel() {
        this.viewModel = new AddTogetherViewModel(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
