package travel.nanjing.com.travel.business.travel;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.handarui.baselib.net.RetrofitFactory;
import com.handarui.baselib.util.RequestBeanMaker;
import com.zhexinit.ov.common.bean.RequestBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import travel.nanjing.com.travel.R;
import travel.nanjing.com.travel.business.api.helper.TrainQuery;
import travel.nanjing.com.travel.business.api.model.bo.DivisionBo;
import travel.nanjing.com.travel.business.api.model.bo.ScenicSpotBo;
import travel.nanjing.com.travel.business.api.model.bo.TrainBo;
import travel.nanjing.com.travel.business.api.service.DevisionService;
import travel.nanjing.com.travel.business.api.service.ScenicSpotService;
import travel.nanjing.com.travel.databinding.ActivityTravelBinding;
import travel.nanjing.com.travel.util.RxUtils;

public class TravelActivity extends AppCompatActivity {

    private ActivityTravelBinding dataBinding;
    private OptionsPickerView pickerView;
    private TravelPlaceAdapter travelPlaceAdapter;
    private TravelTypeAdapter typeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_travel);
        getProvince();
        dataBinding.startProvience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initOptionPicker("start");
            }
        });
        dataBinding.goProvience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initOptionPicker("stop");
            }
        });
        dataBinding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getByDivisionId(endInfo.getId());
            }
        });
        LinearLayoutManager layout = new LinearLayoutManager(this);
        dataBinding.jingdian.setLayoutManager(layout);
        travelPlaceAdapter = new TravelPlaceAdapter(this);
        dataBinding.jingdian.setAdapter(travelPlaceAdapter);

        dataBinding.goType.setLayoutManager(new LinearLayoutManager(this));
        typeAdapter = new TravelTypeAdapter(this);
        dataBinding.goType.setAdapter(typeAdapter);
    }

    List<String> province = new ArrayList<>();
    List<String> city = new ArrayList<>();
    DivisionBo startInfo = new DivisionBo();
    DivisionBo endInfo = new DivisionBo();

    private void initOptionPicker(final String type) {//条件选择器初始化
        pickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                getCityInfo(provinceBo.get(options1).getCode(), type);
                String tx = province.get(options1);
                if (type.equals("start")) {
                    dataBinding.startCity.setText(tx);
                } else {
                    dataBinding.goCity.setText(tx);
                }
            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.BLACK)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.YELLOW)
                .setSubmitColor(Color.YELLOW)
                .setTextColorCenter(Color.LTGRAY)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("省", "市", "区")
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();

        pickerView.setPicker(province);//一级选择器
        pickerView.show();
    }

    private void setCityInfo(final String type) {
        OptionsPickerView pickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = city.get(options1);
                if (type.equals("start")) {
                    dataBinding.startCity.setText(tx);
                    startInfo = cityBo.get(options1);
                } else {
                    endInfo = cityBo.get(options1);
                    dataBinding.goCity.setText(tx);
                }
            }
        })
                .setTitleText("城市选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(0, 1)//默认选中项
                .setBgColor(Color.BLACK)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.YELLOW)
                .setSubmitColor(Color.YELLOW)
                .setTextColorCenter(Color.LTGRAY)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("省", "市", "区")
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();

        pickerView.setPicker(city);//一级选择器
        pickerView.show();
    }

    List<DivisionBo> provinceBo = new ArrayList<>();
    List<DivisionBo> cityBo = new ArrayList<>();

    public void getProvince() {
        DevisionService restService = RetrofitFactory.createRestService(DevisionService.class);
        RxUtils.wrapRestCall(restService.selectProvinces()).subscribe(new Consumer<List<DivisionBo>>() {
            @Override
            public void accept(List<DivisionBo> divisionBos) throws Exception {
                province.clear();
                for (DivisionBo divisionBo : divisionBos) {
                    province.add(divisionBo.getName());
                }
                provinceBo.clear();
                provinceBo.addAll(divisionBos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    public void getCityInfo(String code, final String type) {
        DevisionService restService = RetrofitFactory.createRestService(DevisionService.class);
        RxUtils.wrapRestCall(restService.selectByParentCode(code)).subscribe(new Consumer<List<DivisionBo>>() {
            @Override
            public void accept(List<DivisionBo> divisionBos) throws Exception {
                city.clear();
                for (DivisionBo divisionBo : divisionBos) {
                    city.add(divisionBo.getName());
                }
                cityBo.clear();
                cityBo.addAll(divisionBos);
                setCityInfo(type);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    public void getByDivisionId(Long id) {

        ScenicSpotService restService = RetrofitFactory.createRestService(ScenicSpotService.class);
        RequestBean<TrainQuery> trainQueryRequestBean = RequestBeanMaker.getRequestBean();
        TrainQuery param = new TrainQuery();
        param.setStartStation(startInfo.getName().substring(0,startInfo.getName().indexOf("市")));
        param.setEndStation(endInfo.getName().substring(0,endInfo.getName().indexOf("市")));
        trainQueryRequestBean.setParam(param);
        RxUtils.wrapRestCall(restService.queryTrain(trainQueryRequestBean)).subscribe(new Consumer<List<TrainBo>>() {
            @Override
            public void accept(List<TrainBo> trainBos) throws Exception {
                typeAdapter.setData(trainBos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i(TAG, "accept: " + throwable.getMessage());
                Toast.makeText(TravelActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        RequestBean<Long> requestBean = RequestBeanMaker.getRequestBean();
        RxUtils.wrapRestCall(restService.getByDivisionId(requestBean)).subscribe(new Consumer<List<ScenicSpotBo>>() {
            @Override
            public void accept(List<ScenicSpotBo> scenicSpotBos) throws Exception {
                Log.i(TAG, "accept: " + scenicSpotBos);
                getJingDianTuiJian(scenicSpotBos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i(TAG, "accept: " + throwable.getMessage());
                Toast.makeText(TravelActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 获取景点推荐方式
     *
     * @param id
     */
    public void getJingDianTuiJian(List<ScenicSpotBo> id) {
        List<Long> ids = new ArrayList<>();
        for (ScenicSpotBo scenicSpotBo : id) {
            ids.add(scenicSpotBo.getId());
        }

        ScenicSpotService restService = RetrofitFactory.createRestService(ScenicSpotService.class);
        RequestBean<List<Long>> requestBean = RequestBeanMaker.getRequestBean();
        requestBean.setParam(ids);
        RxUtils.wrapRestCall(restService.sortByLevel(requestBean)).subscribe(new Consumer<List<ScenicSpotBo>>() {
            @Override
            public void accept(List<ScenicSpotBo> scenicSpotBos) throws Exception {
                Log.i(TAG, "accept: " + scenicSpotBos);

                travelPlaceAdapter.setData(scenicSpotBos);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.i(TAG, "accept: " + throwable.getMessage());
                Toast.makeText(TravelActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private static final String TAG = "TravelActivity";
}
