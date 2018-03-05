package travel.nanjing.com.travel.business.together;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.handarui.baselib.util.RxUtil;
import com.handarui.iqfun.business.base.BaseVMFragment;
import com.zhexinit.ov.common.bean.RequestBean;

import java.util.List;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.api.model.bo.MateNoteBo;
import travel.nanjing.com.travel.business.api.service.MateNoteService;
import travel.nanjing.com.travel.business.together.addTogether.AddTogetherActivity;
import travel.nanjing.com.travel.databinding.FragmentTogetherBinding;
import travel.nanjing.com.travel.util.RxUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class TogetherFragment extends BaseVMFragment<TogetherFragment, TogetherViewModel> {


    private TogetherAdapter adapter;
    private Object contentAll;

    public TogetherFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTogetherBinding dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_together, container, false);
        dataBinding.setViewModel(new TogetherViewModel(this));

        dataBinding.addTogether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddTogetherActivity.class));
            }
        });

        dataBinding.togetherRv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TogetherAdapter(getContext());
        adapter.onclick = new TogetherAdapter.Onclick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), TogetherDetailActivity.class);
                intent.putExtra("recordId", adapter.getData().get(position).getId());
                startActivity(intent);
            }
        };
        dataBinding.togetherRv.setAdapter(adapter);

        Bundle arguments = getArguments();
        if (arguments != null) {
            long userId = arguments.getLong("userId");
            if (userId != 0) {
                getContentById(userId);
                dataBinding.addTogether.setVisibility(View.INVISIBLE);
            }
        } else {
            getContentAll();
            dataBinding.addTogether.setVisibility(View.VISIBLE);
        }

        return dataBinding.getRoot();
    }

    private void getContentById(long userId) {
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(userId);


        MateNoteService service = RetrofitFactory.createRestService(MateNoteService.class);
        RxUtil.wrapRestCall(service.getMateNoteListByUserId(requestBean), requestBean.getReqId())
                .subscribe(new Consumer<List<MateNoteBo>>() {
                    @Override
                    public void accept(List<MateNoteBo> mateNoteBos) throws Exception {
                        adapter.setData(mateNoteBos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "accept: " + throwable.getMessage());
                    }
                });
    }

    @Override
    public void initViewModel() {
        viewModel = new TogetherViewModel(this);
    }

    public void getContentAll() {
        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(-1L);

        MateNoteService service = RetrofitFactory.createRestService(MateNoteService.class);
        RxUtils.wrapRestCall(service.getMateNoteList())
                .subscribe(new Consumer<List<MateNoteBo>>() {
                    @Override
                    public void accept(List<MateNoteBo> mateNoteBos) throws Exception {
                        adapter.setData(mateNoteBos);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i(TAG, "accept: " + throwable.getMessage());
                    }
                });
    }

    private static final String TAG = "TogetherFragment";
}
