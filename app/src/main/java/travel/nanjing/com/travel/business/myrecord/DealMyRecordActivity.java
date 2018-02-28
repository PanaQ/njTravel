package travel.nanjing.com.travel.business.myrecord;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.zhexinit.ov.common.query.ListBean;

import java.util.List;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.api.bo.BaseNoteBo;
import travel.nanjing.com.travel.api.bo.UserBo;
import travel.nanjing.com.travel.api.service.NoteService;
import travel.nanjing.com.travel.api.service.UserService;
import travel.nanjing.com.travel.business.MainActivity;

public class DealMyRecordActivity extends AppCompatActivity {

    private static final String TAG = "DealMyRecordActivity";

    private MenuAdapter adapter;

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

            }
        });
        adapter = new MenuAdapter(this);
        swipeMenuRecyclerView.setAdapter(adapter);
        requestMyRecord();
    }

    private void requestMyRecord() {
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(LoginUtils.INSTANCE.getId());

        RxUtil.wrapRestCall(RetrofitFactory.createRestService(NoteService.class).getNoteListByMine(requestBean), requestBean.getReqId())
                .subscribe(new Consumer<ListBean<BaseNoteBo>>() {
                    @Override
                    public void accept(ListBean<BaseNoteBo> baseNoteBoListBean) throws Exception {
                        adapter.setData(baseNoteBoListBean.data);
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

class MenuAdapter extends SwipeMenuAdapter<MenuAdapter.DefaultViewHolder> {

    Context context;
    private List<BaseNoteBo> data;

    public MenuAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(context).inflate(R.layout.item_my_record, null, false);
    }

    @Override
    public MenuAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.DefaultViewHolder holder, int position) {
    }

    public void setData(List<BaseNoteBo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class DefaultViewHolder extends RecyclerView.ViewHolder {
        public DefaultViewHolder(View itemView) {
            super(itemView);
        }
    }
}