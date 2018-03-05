package travel.nanjing.com.travel.business.myrecord;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.handarui.baselib.exception.SuccessException;
import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.util.LoginUtils;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zhexinit.ov.common.bean.RequestBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.api.model.bo.MateNoteBo;
import travel.nanjing.com.travel.business.api.service.MateNoteService;
import travel.nanjing.com.travel.util.RxUtils;

public class DealMyTogetherActivity extends AppCompatActivity {

    private static final String TAG = "DealMyRecordActivity";

    private MenuAdapters adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_my_record);
        SwipeMenuRecyclerView swipeMenuRecyclerView = findViewById(R.id.recycler_view);
        // 设置菜单创建器。
        swipeMenuRecyclerView.setHasFixedSize(true);
        swipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        swipeMenuRecyclerView.setSwipeMenuItemClickListener(new OnSwipeMenuItemClickListener() {
            @Override
            public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
                deletRecord(adapter.getData().get(adapterPosition));
            }
        });
        adapter = new MenuAdapters(this);
        swipeMenuRecyclerView.setAdapter(adapter);
        requestMyRecord();
    }

    private void deletRecord(MateNoteBo data) {
        final RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(data.getId());

        RxUtil.wrapRestCall(RetrofitFactory.createRestService(MateNoteService.class)
                .deleteMateNote(requestBean), requestBean.getReqId())
                .subscribe(new Consumer<Void>() {
                    @Override
                    public void accept(Void aVoid) throws Exception {
                        requestMyRecord();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable instanceof SuccessException) {
                            requestMyRecord();
                        } else {
                            Toast.makeText(DealMyTogetherActivity.this, "删除失败", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void requestMyRecord() {
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(LoginUtils.INSTANCE.getId());

        RxUtils.wrapRestCall(RetrofitFactory.createRestService(MateNoteService.class)
                .getMateNoteListByMine())
                .subscribe(new Consumer<List<MateNoteBo>>() {
                    @Override
                    public void accept(List<MateNoteBo> mateNoteBos) throws Exception {
                        adapter.setData(mateNoteBos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept:getUserInfo " + throwable.getMessage());
                    }
                });

    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {

            int width = getResources().getDimensionPixelSize(R.dimen.item_height);
            int height = getResources().getDimensionPixelSize(R.dimen.item_height);
            SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext())
                    .setBackgroundDrawable(R.drawable.select_white_gray)
                    .setImage(R.mipmap.delete) // 图标。
                    .setText("删除") // 文字。
                    .setTextSize(16) // 文字大小。
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
        }
    };
}

class MenuAdapters extends SwipeMenuAdapter<MenuAdapters.DefaultViewHolder> {

    Context context;
    private List<MateNoteBo> data = new ArrayList<>();

    public MenuAdapters(Context context) {
        this.context = context;
    }

    public List<MateNoteBo> getData() {
        return data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(context).inflate(R.layout.item_my_record, null, false);
    }

    @Override
    public DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(DefaultViewHolder holder, int position) {
        holder.content.setText(data.get(position).getContent());
        holder.title.setText(data.get(position).getTitle());
    }

    public void setData(List<MateNoteBo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class DefaultViewHolder extends RecyclerView.ViewHolder {

        private final TextView content;
        private final TextView title;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            content = ((TextView) itemView.findViewById(R.id.recordContent));
            title = ((TextView) itemView.findViewById(R.id.recordTitle));
        }
    }
}