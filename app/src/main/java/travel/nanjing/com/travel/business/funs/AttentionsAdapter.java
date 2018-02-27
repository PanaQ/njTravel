package travel.nanjing.com.travel.business.funs;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.api.bo.AttentionBo;
import travel.nanjing.com.travel.api.bo.MateNoteBo;

/**
 * Created by zx on 2018/2/8 0008.
 */

public class AttentionsAdapter extends RecyclerView.Adapter<AttentionsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private List<AttentionBo> data  ;

    public AttentionsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public AttentionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = DataBindingUtil.inflate(inflater, R.layout.item_attentions, parent, false).getRoot();

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(AttentionsAdapter.ViewHolder holder, int position) {
//        ItemTravelTogetherBinding binding = DataBindingUtil.getBinding(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public void setData(List<AttentionBo> data) {
        this.data = data;
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
