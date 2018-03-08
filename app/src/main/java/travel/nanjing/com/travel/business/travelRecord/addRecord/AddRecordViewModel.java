package travel.nanjing.com.travel.business.travelRecord.addRecord;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.handarui.baselib.exception.SuccessException;
import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseViewModel;
import com.zhexinit.ov.common.bean.RequestBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import travel.nanjing.com.travel.business.api.model.bo.BaseNoteBo;
import travel.nanjing.com.travel.business.api.service.NoteService;
import travel.nanjing.com.travel.util.RxUtils;

/**
 * Created by zx on 2018/2/11 0011.
 */

public class AddRecordViewModel extends BaseViewModel<AddRecordActivity> {

    private static final String TAG = "AddRecordViewModel";
    private List<String> picAddress = new ArrayList<>();

    protected ImageView clickView;

    public AddRecordViewModel(AddRecordActivity view) {
        super(view);
    }


    public void addRecord(View view) {
        for (File file : this.getView().getFiles()) {
            requestPicture(file);
        }
    }

    public void addRecord(List<String> strings) {
        RequestBean<BaseNoteBo> requestBean = RequestBeanMaker.getRequestBean();
        BaseNoteBo param = new BaseNoteBo();
        param.setTitle(this.getView().binding.title.getText().toString());
        String content = this.getView().binding.content.getText().toString();
        for (String string : strings) {
            content+="abcd"+string;
        }
        String[] https = content.split("abcd");
        param.setContent(content);
        requestBean.setParam(param);
        NoteService restService = RetrofitFactory.createRestService(NoteService.class);
        RxUtil.wrapRestCall(restService.addNote(requestBean),
                requestBean.getReqId()).subscribe(new Consumer<Void>() {
            @Override
            public void accept(Void aVoid) throws Exception {
                getView().finish();
                Toast.makeText(getView(), "发布成功", Toast.LENGTH_LONG).show();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof SuccessException) {
                    getView().finish();
                    Toast.makeText(getView(), "发布成功", Toast.LENGTH_LONG).show();
                } else {
                    Log.i(TAG, "accept: " + throwable.getMessage());
                }
            }
        });
    }


    public void requestPicture(File fileName) {

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), fileName);
        MultipartBody.Part body = MultipartBody.Part.createFormData("picture", fileName.getName(), requestFile);

        String descriptionString = "picture";

        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        RxUtils.wrapRestCall(RetrofitFactory.createRestService(NoteService.class)
                .uploadPicture(body, description))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        picAddress.add(s);
                        if (picAddress.size()==getView().getFiles().size()){
                            addRecord(picAddress);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getView(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e(TAG, "accept: " + throwable.getMessage());
                    }
                });

    }

    public void addPicture(View view) {
        clickView = (ImageView) view;
        this.getView().startPicture();
    }
}
