package travel.nanjing.com.travel.business.travel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.api.model.bo.TrainBo;

/**
 * Created by wang on 2018/3/10.
 */

public class TravelTypeAdapter extends RecyclerView.Adapter<TravelTypeAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    Context context;
    private List<TrainBo> data = new ArrayList<>();

    public TravelTypeAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TravelTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TravelTypeAdapter.ViewHolder(inflater.inflate(R.layout.item_travel_type, parent, false));
    }

    @Override
    public void onBindViewHolder(TravelTypeAdapter.ViewHolder holder, int position) {
        holder.tainName.setText(data.get(position).getTrainNo());
        holder.start.setText(data.get(position).getStartStation());
        holder.end.setText(data.get(position).getEndStation());
        holder.price.setText(data.get(position).getPrice() + "");
        holder.time.setText(data.get(position).getRunTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<TrainBo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tainName;
        private final TextView start;
        private final TextView end;
        private final TextView time;
        private final TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            tainName = ((TextView) itemView.findViewById(R.id.tainName));
            start = ((TextView) itemView.findViewById(R.id.start));
            end = ((TextView) itemView.findViewById(R.id.stop));
            time = ((TextView) itemView.findViewById(R.id.time));
            price = ((TextView) itemView.findViewById(R.id.price));
        }
    }
}
