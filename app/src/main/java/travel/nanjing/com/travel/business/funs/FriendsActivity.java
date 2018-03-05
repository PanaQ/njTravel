package travel.nanjing.com.travel.business.funs;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.iqfun.business.base.BaseVMActivity;
import com.zhexinit.ov.common.bean.RequestBean;

import java.util.List;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.api.model.bo.AttentionBo;
import travel.nanjing.com.travel.business.api.service.FollowService;
import travel.nanjing.com.travel.business.friends.UserInfoActivity;
import travel.nanjing.com.travel.databinding.ActivityFriendsBinding;
import travel.nanjing.com.travel.util.RxUtils;

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
            final FunsAdapter adapter = new FunsAdapter(this);
            adapter.onclick = new FunsAdapter.Onclick() {
                @Override
                public void onItemClick(int position) {
                    startActivity(adapter.getData().get(position));
                }
            };
            binding.friendRv.setAdapter(adapter);
            requestFans(adapter);
        } else {
            final AttentionsAdapter adapter = new AttentionsAdapter(this);
            adapter.onclick = new AttentionsAdapter.Onclick() {
                @Override
                public void onItemClick(int position) {
                    startActivity(adapter.getData().get(position));
                }
            };
            binding.friendRv.setAdapter(adapter);
            requestAttention(adapter);
        }
    }

    private void requestFans(final FunsAdapter adapter) {
        RequestBean<Object> requestBean = RequestBeanMaker.getRequestBean();

        FollowService restService = RetrofitFactory.createRestService(FollowService.class);
        RxUtils.wrapRestCall(restService.getFansList()).subscribe(new Consumer<List<AttentionBo>>() {
            @Override
            public void accept(List<AttentionBo> attentionBos) throws Exception {
                adapter.setData(attentionBos);
                if (attentionBos.size() > 0) {
                    binding.empty.setVisibility(View.GONE);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept: " + throwable.getMessage());
            }
        });
    }

    private static final String TAG = "FriendsActivity";

    private void requestAttention(final AttentionsAdapter adapter) {
        RequestBean<Object> requestBean = RequestBeanMaker.getRequestBean();

        FollowService restService = RetrofitFactory.createRestService(FollowService.class);
        RxUtils.wrapRestCall(restService.getAttentionList()).subscribe(new Consumer<List<AttentionBo>>() {
            @Override
            public void accept(List<AttentionBo> attentionBos) throws Exception {
                adapter.setData(attentionBos);
                if (attentionBos.size() > 0) {
                    binding.empty.setVisibility(View.GONE);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept: " + throwable.getMessage());
            }
        });
    }

    private void startActivity(AttentionBo attentionBo) {
        Intent intent = new Intent(FriendsActivity.this, UserInfoActivity.class);
        intent.putExtra("type", "user");
        intent.putExtra("id", attentionBo.getId());
        intent.putExtra("name", attentionBo.getName());
        startActivity(intent);
    }

    @Override
    public void initViewModel() {
        this.viewModel = new FriendsViewModel(this);
    }
}
