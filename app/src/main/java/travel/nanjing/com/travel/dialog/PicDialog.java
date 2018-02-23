package travel.nanjing.com.travel.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.travelRecord.addRecord.AddRecordActivity;

/**
 * Created by zx on 2018/2/23 0023.
 */

public class PicDialog extends Dialog {

    private Context context;

    public PicDialog(@NonNull Context context) {
        this(context, R.style.CustomDialogStyle);
    }

    public PicDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.CustomDialogStyle);
        this.context = context;
        init();
    }

    private void init() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM);
            window.getAttributes().width = ViewGroup.LayoutParams.MATCH_PARENT;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pic);

        findViewById(R.id.album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddRecordActivity) context).startPicture();
            }
        });

    }
}
