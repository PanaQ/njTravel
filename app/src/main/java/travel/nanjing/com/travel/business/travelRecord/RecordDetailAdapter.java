package travel.nanjing.com.travel.business.travelRecord;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.util.Constant;

/**
 * Created by zx on 2018/2/8 0008.
 */

public class RecordDetailAdapter extends RecyclerView.Adapter<RecordDetailAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<String> data = new ArrayList<>();
    private Context context;

    public RecordDetailAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setData(String data) {

        this.data.addAll(Arrays.asList(data.split("abcd")));
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View root = LayoutInflater.from(context).inflate(R.layout.item_image, null);
            return new ImgViewHolder(root);
        } else if (viewType == 2) {
            View root = LayoutInflater.from(context).inflate(R.layout.item_text, null);
            return new TextViewHolder(root);
        } else {
            View root = LayoutInflater.from(context).inflate(R.layout.item_video, null);
            return new VideoViewHolder(root);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ImgViewHolder) {
            String replace = data.get(position).replace("http://localhost:8080", Constant.SERVER_ADDRESS);
            Log.i("", "onBindViewHolder: " + replace);
            Picasso.with(context).load(replace).into(((ImgViewHolder) holder).img);
        }
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).text.setText(data.get(position));
        }
        if (holder instanceof VideoViewHolder) {
            String replace = data.get(position).replace("http://localhost:8080", Constant.SERVER_ADDRESS);
            ((VideoViewHolder) holder).videoView.setVideoPath(replace);
            ((VideoViewHolder) holder).videoView.start();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 3) {
            return 3;
        }
        if (data.get(position).contains("http")) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TextViewHolder extends RecordDetailAdapter.ViewHolder {

        private final TextView text;

        public TextViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }
    }

    public class ImgViewHolder extends RecordDetailAdapter.ViewHolder {

        private final ImageView img;

        public ImgViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
        }
    }

    public class VideoViewHolder extends RecordDetailAdapter.ViewHolder {

        private final VideoView videoView;

        public VideoViewHolder(View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.video);
        }
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
