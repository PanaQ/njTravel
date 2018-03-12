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
import travel.nanjing.com.travel.business.api.model.bo.ScenicSpotBo;

/**
 * Created by wang on 2018/3/10.
 */

public class TravelPlaceAdapter extends RecyclerView.Adapter<TravelPlaceAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    Context context;
    private List<ScenicSpotBo> data = new ArrayList<>();

    public TravelPlaceAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TravelPlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_travel_place, parent, false));
    }

    @Override
    public void onBindViewHolder(TravelPlaceAdapter.ViewHolder holder, int position) {
        holder.name.setText(data.get(position).getName());
        holder.content.setText(data.get(position).getPlayTime().toString());
        holder.level.setText(data.get(position).getLevel()+"");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ScenicSpotBo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final TextView level;
        private final TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            level = itemView.findViewById(R.id.level);
            content = itemView.findViewById(R.id.recordContent);
        }
    }
}
