package travel.nanjing.com.travel.business.funs;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.handarui.iqfun.business.base.BaseVMActivity;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.friends.UserInfoActivity;
import travel.nanjing.com.travel.databinding.ActivityFriendsBinding;

public class FriendsActivity extends BaseVMActivity<FriendsActivity, FriendsViewModel> {

    private ActivityFriendsBinding binding;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getIntent().getStringExtra("type");

        binding = DataBindingUtil.setContentView(this, R.layout.activity_friends);
        binding.setViewModel(this.viewModel);

        binding.friendRv.setLayoutManager(new LinearLayoutManager(this));
        if ("funs".equals(type)) {
            FunsAdapter adapter = new FunsAdapter(this);
            adapter.onclick = new FunsAdapter.Onclick() {
                @Override
                public void onItemClick(int position) {
                    startActivity();
                }
            };
            binding.friendRv.setAdapter(adapter);
        } else {
            AttentionsAdapter adapter = new AttentionsAdapter(this);
            adapter.onclick = new AttentionsAdapter.Onclick() {
                @Override
                public void onItemClick(int position) {
                    startActivity();
                }
            };
            binding.friendRv.setAdapter(adapter);
        }
    }

    private void startActivity() {
        Intent intent = new Intent(FriendsActivity.this, UserInfoActivity.class);
        intent.putExtra("type", "user");
        startActivity(intent);
    }

    @Override
    public void initViewModel() {
        this.viewModel = new FriendsViewModel(this);
    }
}
