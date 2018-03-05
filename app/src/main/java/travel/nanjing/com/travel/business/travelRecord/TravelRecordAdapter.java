package travel.nanjing.com.travel.business.travelRecord;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handarui.iqfun.util.LoginUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.api.model.bo.BaseNoteBo;
import travel.nanjing.com.travel.databinding.ItemTravelRecordBinding;

/**
 * Created by zx on 2018/2/8 0008.
 */

public class TravelRecordAdapter extends RecyclerView.Adapter<TravelRecordAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private ArrayList<BaseNoteBo> data = new ArrayList<>();

    public TravelRecordAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public ArrayList<BaseNoteBo> getData() {
        return data;
    }

    public void setData(ArrayList<BaseNoteBo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = DataBindingUtil.inflate(inflater, R.layout.item_travel_record, parent, false).getRoot();

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemTravelRecordBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.recordContent.setText(data.get(position).getContent());
        binding.recordTitle.setText(data.get(position).getTitle());
        Picasso.with(context).load(LoginUtils.INSTANCE.getAva()).into(binding.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
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
