package travel.nanjing.com.travel.business.funs;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseVMActivity;
import com.zhexinit.ov.common.bean.RequestBean;

import java.util.List;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.api.bo.AttentionBo;
import travel.nanjing.com.travel.api.bo.MateNoteBo;
import travel.nanjing.com.travel.api.service.FollowService;
import travel.nanjing.com.travel.business.friends.UserInfoActivity;
import travel.nanjing.com.travel.databinding.ActivityFriendsBinding;

public class FriendsActivity extends BaseVMActivity<FriendsActivity, FriendsViewModel> {

    private static final String TAG = "FriendsActivity";

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
            requestFans(adapter);
        } else {
            AttentionsAdapter adapter = new AttentionsAdapter(this);
            adapter.onclick = new AttentionsAdapter.Onclick() {
                @Override
                public void onItemClick(int position) {
                    startActivity();
                }
            };
            binding.friendRv.setAdapter(adapter);
            requestAttention(adapter);
        }
    }

    private void requestFans(final FunsAdapter adapter) {
        RequestBean<Object> requestBean = RequestBeanMaker.getRequestBean();


        FollowService restService = RetrofitFactory.createRestService(FollowService.class);
        RxUtil.wrapRestCall(restService.getFansList(), requestBean.getReqId()).subscribe(new Consumer<List<AttentionBo>>() {
            @Override
            public void accept(List<AttentionBo> attentionBos) throws Exception {
                adapter.setData(attentionBos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept: "+throwable.getMessage());
            }
        });
    }

    private void requestAttention(final AttentionsAdapter adapter) {
        RequestBean<Object> requestBean = RequestBeanMaker.getRequestBean();


        FollowService restService = RetrofitFactory.createRestService(FollowService.class);
        RxUtil.wrapRestCall(restService.getAttentionList(), requestBean.getReqId()).subscribe(new Consumer<List<AttentionBo>>() {
            @Override
            public void accept(List<AttentionBo> attentionBos) throws Exception {
                adapter.setData(attentionBos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept: "+throwable.getMessage());
            }
        });
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
