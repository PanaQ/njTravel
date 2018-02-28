package travel.nanjing.com.travel.business.together;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhexinit.ov.common.query.ListBean;

import java.util.List;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.api.bo.MateNoteBo;
import travel.nanjing.com.travel.databinding.ItemTravelTogetherBinding;

/**
 * Created by zx on 2018/2/8 0008.
 */

public class TogetherAdapter extends RecyclerView.Adapter<TogetherAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private ListBean<MateNoteBo> data;
    private List<MateNoteBo> adapter;

    public TogetherAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TogetherAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = DataBindingUtil.inflate(inflater, R.layout.item_travel_together, parent, false).getRoot();

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(TogetherAdapter.ViewHolder holder, int position) {
        ItemTravelTogetherBinding binding = DataBindingUtil.getBinding(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public void setData(ListBean<MateNoteBo> data) {
        this.data = data;
        adapter = this.data.data;
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
