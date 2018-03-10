package travel.nanjing.com.travel.business.travel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import travel.nanjing.com.travel.R;

/**
 * Created by wang on 2018/3/10.
 */

public class TravelTypeAdapter extends RecyclerView.Adapter<TravelTypeAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    Context context;

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
