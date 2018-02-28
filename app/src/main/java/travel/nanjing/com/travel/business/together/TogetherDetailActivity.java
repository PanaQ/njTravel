package travel.nanjing.com.travel.business.together;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseVMActivity;
import com.zhexinit.ov.common.bean.RequestBean;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.api.bo.MateNoteBo;
import travel.nanjing.com.travel.api.service.MateNoteService;
import travel.nanjing.com.travel.databinding.ActivityTogetherDetailBinding;

public class TogetherDetailActivity extends BaseVMActivity<TogetherDetailActivity, TogetherDetailViewModel> {

    private static final String TAG = "TogetherDetailActivity";

    private long recordId;
    private ActivityTogetherDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_together_detail);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recordId = getIntent().getLongExtra("recordId", -1);
        requestData();
    }

    private void requestData() {
        if (recordId == -1) {
            return;
        }

        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(recordId);

        MateNoteService service = RetrofitFactory.createRestService(MateNoteService.class);
        RxUtil.wrapRestCall(service.getMateNoteById(requestBean), requestBean.getReqId())
                .subscribe(new Consumer<MateNoteBo>() {
                    @Override
                    public void accept(MateNoteBo mateNoteBo) throws Exception {
                        binding.phone.setText(mateNoteBo.getPhone());
                        binding.content.setText(mateNoteBo.getContent());
                        binding.title.setText(mateNoteBo.getTitle());
                        binding.userName.setText(mateNoteBo.getUserName());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "accept: " + throwable.getMessage());
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initViewModel() {
        this.viewModel = new TogetherDetailViewModel(this);
    }
}
