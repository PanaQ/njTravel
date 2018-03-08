package travel.nanjing.com.travel.business.funs;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handarui.iqfun.util.LoginUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.api.model.bo.AttentionBo;
import travel.nanjing.com.travel.databinding.ItemAttentionsBinding;

/**
 * Created by zx on 2018/2/8 0008.
 */

public class FunsAdapter extends RecyclerView.Adapter<FunsAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private List<AttentionBo> data = new ArrayList<>();

    public FunsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = DataBindingUtil.inflate(inflater, R.layout.item_attentions, parent, false).getRoot();

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemAttentionsBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.attention.setVisibility(View.INVISIBLE);
        binding.name.setText(data.get(position).getName());
        Picasso.with(context).load(LoginUtils.INSTANCE.getAva()).into(binding.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<AttentionBo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public List<AttentionBo> getData() {
        return data;
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
