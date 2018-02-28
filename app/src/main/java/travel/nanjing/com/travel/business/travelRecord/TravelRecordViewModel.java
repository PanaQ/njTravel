package travel.nanjing.com.travel.business.travelRecord;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseViewModel;
import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.query.ListBean;
import com.zhexinit.ov.common.query.SortPagerQuery;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;
import okhttp3.OkHttpClient;
import travel.nanjing.com.travel.api.bo.BaseNoteBo;
import travel.nanjing.com.travel.api.bo.MateNoteBo;
import travel.nanjing.com.travel.api.service.MateNoteService;
import travel.nanjing.com.travel.api.service.NoteService;
import travel.nanjing.com.travel.business.travelRecord.addRecord.AddRecordActivity;

import static me.nereo.multi_image_selector.MultiImageSelectorFragment.TAG;

/**
 * Created by zx on 2018/2/7 0007.
 */

public class TravelRecordViewModel extends BaseViewModel<TravelRecordFragment> {

    private static final String TAG = "TravelRecordViewModel";

    public TravelRecordViewModel(TravelRecordFragment view) {
        super(view);
    }

    public void addRecord(View view) {
        this.getView().getContext().startActivity(new Intent(this.getView().getContext(), AddRecordActivity.class));
    }

    public void getAllContent(final TravelRecordAdapter adapter){
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(1l);


        NoteService service = RetrofitFactory.createRestService(NoteService.class);
        RxUtil.wrapRestCall(service.getNoteList(requestBean), requestBean.getReqId())
                .subscribe(new Consumer<ListBean<BaseNoteBo>>() {
                    @Override
                    public void accept(ListBean<BaseNoteBo> mateNoteBoListBean) throws Exception {
                        adapter.setData((ArrayList<BaseNoteBo>) mateNoteBoListBean.data);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "accept: " + throwable.getMessage());
                    }
                });
    }
}
