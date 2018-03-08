package travel.nanjing.com.travel.business.funs;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.handarui.baselib.exception.SuccessException;
import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.util.LoginUtils;
import com.squareup.picasso.Picasso;
import com.zhexinit.ov.common.bean.RequestBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.api.model.bo.AttentionBo;
import travel.nanjing.com.travel.business.api.model.bo.MateNoteBo;
import travel.nanjing.com.travel.business.api.service.FollowService;
import travel.nanjing.com.travel.databinding.ItemAttentionsBinding;

/**
 * Created by zx on 2018/2/8 0008.
 */

public class AttentionsAdapter extends RecyclerView.Adapter<AttentionsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private List<AttentionBo> data = new ArrayList<>();

    public AttentionsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = DataBindingUtil.inflate(inflater, R.layout.item_attentions, parent, false).getRoot();

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ItemAttentionsBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.name.setText(data.get(position).getName());
        Picasso.with(context).load(LoginUtils.INSTANCE.getAva()).into(binding.imageView);
        binding.attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
                requestBean.setParam(data.get(position).getId());

                FollowService restService = RetrofitFactory.createRestService(FollowService.class);
                RxUtil.wrapRestCall(restService.cancelFollow(requestBean), requestBean.getReqId()).subscribe(new Consumer<MateNoteBo>() {
                    @Override
                    public void accept(MateNoteBo mateNoteBo) throws Exception {
                        Toast.makeText(context, "取消关注成功", Toast.LENGTH_LONG).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (throwable instanceof SuccessException) {
                            Toast.makeText(context, "取消关注成功", Toast.LENGTH_LONG).show();
                        }
                        Log.e(TAG, "accept: " + throwable.getMessage());
                        Toast.makeText(context, "取消关注失败", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private static final String TAG = "AttentionsAdapter";

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<AttentionBo> getData() {
        return data;
    }

    public void setData(List<AttentionBo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onclick != null) {
                        onclick.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    Onclick onclick;

    interface Onclick {
        void onItemClick(int position);
    }
}
