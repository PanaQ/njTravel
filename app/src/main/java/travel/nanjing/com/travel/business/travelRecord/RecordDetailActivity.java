package travel.nanjing.com.travel.business.travelRecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.zhexinit.ov.common.bean.RequestBean;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.api.bo.MateNoteBo;
import travel.nanjing.com.travel.api.service.FollowService;

/**
 *
 */
public class RecordDetailActivity extends AppCompatActivity {

    private RecyclerView noteRv;
    private RecyclerView contentRv;
    private NoteAdapter adapter;
    private ImageView attention;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteRv = ((RecyclerView) findViewById(R.id.noteRv));
        contentRv = ((RecyclerView) findViewById(R.id.contentRv));
        attention = ((ImageView) findViewById(R.id.attention));

        noteRv.setLayoutManager(new LinearLayoutManager(this));
        contentRv.setLayoutManager(new LinearLayoutManager(this));

        adapter = new NoteAdapter(this);
        noteRv.setAdapter(adapter);

        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1 == 1) {
                    addAttention();
                } else {
                    cancleAttention();
                }
                Toast.makeText(RecordDetailActivity.this, "关注", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cancleAttention() {
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        //todo userId
        requestBean.setParam(1L);

        FollowService restService = RetrofitFactory.createRestService(FollowService.class);
        RxUtil.wrapRestCall(restService.cancelFollow(requestBean), requestBean.getReqId()).subscribe(new Consumer<MateNoteBo>() {
            @Override
            public void accept(MateNoteBo mateNoteBo) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    private void addAttention() {
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        //todo userId
        requestBean.setParam(1L);

        FollowService restService = RetrofitFactory.createRestService(FollowService.class);
        RxUtil.wrapRestCall(restService.addFollow(requestBean), requestBean.getReqId()).subscribe(new Consumer<Void>() {
            @Override
            public void accept(Void aVoid) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

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
}
