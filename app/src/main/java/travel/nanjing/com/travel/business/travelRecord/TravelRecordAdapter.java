package travel.nanjing.com.travel.business.travelRecord;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.databinding.ItemTravelRecordBinding;

/**
 * Created by zx on 2018/2/8 0008.
 */

public class TravelRecordAdapter extends RecyclerView.Adapter<TravelRecordAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private Context context;

    public TravelRecordAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TravelRecordAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = DataBindingUtil.inflate(inflater, R.layout.item_travel_record, parent, false).getRoot();

        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(TravelRecordAdapter.ViewHolder holder, int position) {
        ItemTravelRecordBinding binding = DataBindingUtil.getBinding(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return 20;
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
