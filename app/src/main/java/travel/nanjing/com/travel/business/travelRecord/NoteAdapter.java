package travel.nanjing.com.travel.business.travelRecord;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.api.model.bo.NoteCommentBo;
import travel.nanjing.com.travel.databinding.ItemTravelNoteBinding;

/**
 * Created by zx on 2018/2/8 0008.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private Context context;
    private List<NoteCommentBo> data = new ArrayList<>();

    public NoteAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = DataBindingUtil.inflate(inflater, R.layout.item_travel_note, parent, false).getRoot();
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemTravelNoteBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.no.setText(position + "楼");
        binding.note.setText(data.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<NoteCommentBo> data) {
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
