package travel.nanjing.com.travel.business.travelRecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.zhexinit.ov.common.bean.RequestBean;

import java.util.List;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.api.bo.MateNoteBo;
import travel.nanjing.com.travel.api.bo.NoteBo;
import travel.nanjing.com.travel.api.bo.NoteCommentBo;
import travel.nanjing.com.travel.api.service.FollowService;
import travel.nanjing.com.travel.api.service.NoteService;

/**
 *
 */
public class RecordDetailActivity extends AppCompatActivity {

    private RecyclerView noteRv;
    private RecyclerView contentRv;
    private NoteAdapter noteAdapter;
    private ImageView attention;
    private Button sendNote;
    private boolean isFollow;
    long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        noteRv = ((RecyclerView) findViewById(R.id.noteRv));
        contentRv = ((RecyclerView) findViewById(R.id.contentRv));
        attention = ((ImageView) findViewById(R.id.attention));
        sendNote = ((Button) findViewById(R.id.sendNote));


        noteRv.setLayoutManager(new LinearLayoutManager(this));
        contentRv.setLayoutManager(new LinearLayoutManager(this));

        noteAdapter = new NoteAdapter(this);
        noteRv.setAdapter(noteAdapter);

        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFollow) {
                    addAttention();
                } else {
                    cancleAttention();
                }
                Toast.makeText(RecordDetailActivity.this, "关注", Toast.LENGTH_SHORT).show();
            }
        });

        Long recordId = getIntent().getLongExtra("recordId", -1L);
        userId = getIntent().getLongExtra("userId", -1L);
        if (recordId != -1L) {
            getContentById(recordId);
        }
        if (userId != -1L) {
            getIsFollow();
        }

        sendNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void getContentById(Long recordId) {

        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(recordId);

        NoteService service = RetrofitFactory.createRestService(NoteService.class);
        RxUtil.wrapRestCall(service.getNoteById(requestBean), requestBean.getReqId()).subscribe(new Consumer<NoteBo>() {
            @Override
            public void accept(NoteBo noteBo) throws Exception {
                List<NoteCommentBo> noteCommentBoList = noteBo.getNoteCommentBoList();
                noteAdapter.setData(noteCommentBoList);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    private static final String TAG = "RecordDetailActivity";

    private void cancleAttention() {
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        //todo userId
        requestBean.setParam(1L);

        FollowService restService = RetrofitFactory.createRestService(FollowService.class);
        RxUtil.wrapRestCall(restService.cancelFollow(requestBean), requestBean.getReqId()).subscribe(new Consumer<MateNoteBo>() {
            @Override
            public void accept(MateNoteBo mateNoteBo) throws Exception {
                isFollow = false;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    private void addAttention() {
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(userId);

        FollowService restService = RetrofitFactory.createRestService(FollowService.class);
        RxUtil.wrapRestCall(restService.addFollow(requestBean), requestBean.getReqId()).subscribe(new Consumer<Void>() {
            @Override
            public void accept(Void aVoid) throws Exception {
                isFollow = true;
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

    public void getIsFollow() {
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(userId);

        FollowService restService = RetrofitFactory.createRestService(FollowService.class);
        RxUtil.wrapRestCall(restService.isFollow(requestBean), requestBean.getReqId()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                isFollow = aBoolean;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }
}
