package travel.nanjing.com.travel.business.friends;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.handarui.iqfun.business.base.BaseVMActivity;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.together.TogetherFragment;
import travel.nanjing.com.travel.business.travelRecord.TravelRecordFragment;
import travel.nanjing.com.travel.databinding.ActivityUserinfoBinding;

public class UserInfoActivity extends BaseVMActivity<UserInfoActivity, UserViewModel> {

    private ActivityUserinfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_userinfo);
        binding.name.setText("Asd");
        binding.viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        String type = getIntent().getStringExtra("type");
        if (type.equals("me")) {
            binding.setting.setVisibility(View.VISIBLE);
        } else {
            binding.setting.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void initViewModel() {
        this.viewModel = new UserViewModel(this);
    }
}

class PagerAdapter extends FragmentPagerAdapter {

    String[] title = {"游记", "伴游"};

    private final TravelRecordFragment travelRecordFragment = new TravelRecordFragment();
    private final TogetherFragment togetherFragment = new TogetherFragment();
    Fragment[] fragments = new Fragment[2];

    public PagerAdapter(FragmentManager fm) {
        super(fm);
        Bundle args = new Bundle();

        args.putString("userInfo", "userInfo");

        travelRecordFragment.setArguments(args);
        togetherFragment.setArguments(args);

        fragments[0] = travelRecordFragment;
        fragments[1] = togetherFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}