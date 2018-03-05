package travel.nanjing.com.travel.business.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import travel.nanjing.com.travel.business.api.helper.HTTPS;
import travel.nanjing.com.travel.business.api.model.bo.ScenicSpotBo;

@HTTPS
public interface ScenicSpotService {

    @POST("/scenicSpot/getByDivisionId")
    Observable<ResponseBean<List<ScenicSpotBo>>> getByDivisionId(@Body RequestBean<Long> requestBean);

    @POST("/scenicSpot/sortRoute")
    Observable<ResponseBean<List<ScenicSpotBo>>> sortRoute(@Body RequestBean<List<Long>> requestBean);

    @POST("/scenicSpot/sortByLevel")
    Observable<ResponseBean<List<ScenicSpotBo>>> sortByLevel(@Body RequestBean<List<Long>> requestBean);
}
