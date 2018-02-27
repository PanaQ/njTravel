package travel.nanjing.com.travel.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import travel.nanjing.com.travel.api.bo.AttentionBo;
import travel.nanjing.com.travel.api.bo.MateNoteBo;
import travel.nanjing.com.travel.api.helper.HTTPS;

@HTTPS
public interface FollowService {

    @POST("/follow/addFollow")
    Observable<ResponseBean<Void>> addFollow(@Body RequestBean<Long> requestBean);

    @POST("/follow/cancelFollow")
    Observable<ResponseBean<MateNoteBo>> cancelFollow(@Body RequestBean<Long> requestBean);

    @POST("/follow/getAttentionList")
    Observable<ResponseBean<List<AttentionBo>>> getAttentionList();

    @POST("/follow/getFansList")
    Observable<ResponseBean<List<AttentionBo>>> getFansList();

}
