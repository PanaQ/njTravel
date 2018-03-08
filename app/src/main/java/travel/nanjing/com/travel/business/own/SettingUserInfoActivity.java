package travel.nanjing.com.travel.business.own;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.handarui.iqfun.business.base.BaseVMActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.databinding.ActivityMyInfoBinding;
import travel.nanjing.com.travel.util.BitmapUtils;

public class SettingUserInfoActivity extends BaseVMActivity<SettingUserInfoActivity, SettingUserInfoModel> {

    private static final int PICTURE_IMAGE = 1001;
    protected ActivityMyInfoBinding binding;
    protected File fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_info);
        binding.setViewModel(this.viewModel);
        binding.userAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiImageSelector.create()
                        .showCamera(false) // 是否显示相机. 默认为显示
                        .count(1) // 最大选择图片数量, 默认为9. 只有在选择模式为多选时有效
                        .single() // 单选模式
                        .start(SettingUserInfoActivity.this, PICTURE_IMAGE);
            }
        });
        viewModel.init();
    }

    @Override
    public void initViewModel() {
        this.viewModel = new SettingUserInfoModel(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICTURE_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取选取相册图片的地址
                ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Log.d(TAG, "picture  path =" + path.get(0));
                fileName = BitmapUtils.compress(this, path.get(0));
                Picasso.with(this).load(fileName).resize(144, 144).into(this.binding.userAva);
            }
        }
    }

    private static final String TAG = "SettingUserInfoActivity";


}
