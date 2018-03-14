package travel.nanjing.com.travel.business.travelRecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handarui.baselib.exception.SuccessException;
import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.zhexinit.ov.common.bean.RequestBean;

import java.util.List;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.api.model.bo.AddNoteCommentBo;
import travel.nanjing.com.travel.business.api.model.bo.MateNoteBo;
import travel.nanjing.com.travel.business.api.model.bo.NoteBo;
import travel.nanjing.com.travel.business.api.model.bo.NoteCommentBo;
import travel.nanjing.com.travel.business.api.service.FollowService;
import travel.nanjing.com.travel.business.api.service.NoteCommentService;
import travel.nanjing.com.travel.business.api.service.NoteService;

/**
 *
 */
public class RecordDetailActivity extends AppCompatActivity {

    private RecyclerView noteRv;
    private RecyclerView contentRv;
    private NoteAdapter noteAdapter;
    private ImageView attention;
    private Button sendNote;
    private EditText note;
    private TextView title;
    private boolean isFollow;
    long userId;
    private long recordId;
    private String recordContent;

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
        note = ((EditText) findViewById(R.id.note));
        title = ((TextView) findViewById(R.id.title));


        noteRv.setLayoutManager(new LinearLayoutManager(this));
        contentRv.setLayoutManager(new LinearLayoutManager(this));

        noteAdapter = new NoteAdapter(this);
        noteRv.setAdapter(noteAdapter);

        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFollow) {
                    cancleAttention();
                } else {
                    addAttention();
                }
            }
        });

        recordId = getIntent().getLongExtra("recordId", -1L);
        userId = getIntent().getLongExtra("userId", -1L);
        recordContent = getIntent().getStringExtra("recordContent");
        title.setText(getIntent().getStringExtra("title"));

        if (recordId != -1L) {
            getContentById(recordId);
        }
        if (userId != -1L) {
            getIsFollow();
        }

        RecordDetailAdapter adapter = new RecordDetailAdapter(this);
        contentRv.setAdapter(adapter);
        adapter.setData(recordContent);

        sendNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNote();
            }
        });
    }

    private void sendNote() {
        final RequestBean<AddNoteCommentBo> requestBean = RequestBeanMaker.getRequestBean();
        AddNoteCommentBo param = new AddNoteCommentBo();
        param.setNoteId(recordId);
        param.setComment(note.getText().toString());
        requestBean.setParam(param);

        NoteCommentService service = RetrofitFactory.createRestService(NoteCommentService.class);
        RxUtil.wrapRestCall(service.addFollow(requestBean), requestBean.getReqId()).subscribe(new Consumer<Void>() {
            @Override
            public void accept(Void aVoid) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof SuccessException) {
                    Toast.makeText(RecordDetailActivity.this, "发表评论成功", Toast.LENGTH_LONG).show();
                    getContentById(recordId);
                } else {
                    Toast.makeText(RecordDetailActivity.this, "发表评论失败", Toast.LENGTH_LONG).show();
                }
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
                Log.e(TAG, "accept: " + throwable.getMessage());
            }
        });
    }

    private static final String TAG = "RecordDetailActivity";

    private void cancleAttention() {
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        //todo userId
        requestBean.setParam(userId);

        FollowService restService = RetrofitFactory.createRestService(FollowService.class);
        RxUtil.wrapRestCall(restService.cancelFollow(requestBean), requestBean.getReqId()).subscribe(new Consumer<MateNoteBo>() {
            @Override
            public void accept(MateNoteBo mateNoteBo) throws Exception {
                isFollow = false;
                dealFollow(isFollow);
                Toast.makeText(RecordDetailActivity.this, "取消关注成功", Toast.LENGTH_LONG).show();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof SuccessException) {
                    isFollow = false;
                    dealFollow(isFollow);
                    Toast.makeText(RecordDetailActivity.this, "取消关注成功", Toast.LENGTH_LONG).show();
                }
                Log.e(TAG, "accept: " + throwable.getMessage());
                Toast.makeText(RecordDetailActivity.this, "取消关注失败", Toast.LENGTH_LONG).show();
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
                Toast.makeText(RecordDetailActivity.this, "关注成功", Toast.LENGTH_LONG).show();
                dealFollow(isFollow);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof SuccessException) {
                    isFollow = true;
                    Toast.makeText(RecordDetailActivity.this, "关注成功", Toast.LENGTH_LONG).show();
                    dealFollow(isFollow);
                } else {
                    Toast.makeText(RecordDetailActivity.this, "关注失败", Toast.LENGTH_LONG).show();
                }
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
                dealFollow(isFollow);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "accept: " + throwable.getMessage());
            }
        });
    }

    private void dealFollow(boolean isFollow) {
        if (isFollow) {
            attention.setImageResource(R.mipmap.icon_already_attention);
        } else {
            attention.setImageResource(R.mipmap.icon_add_attention);
        }
    }
}
