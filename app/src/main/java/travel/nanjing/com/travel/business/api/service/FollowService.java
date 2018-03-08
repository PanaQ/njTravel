package travel.nanjing.com.travel.business.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import travel.nanjing.com.travel.business.api.helper.HTTPS;
import travel.nanjing.com.travel.business.api.model.bo.AttentionBo;
import travel.nanjing.com.travel.business.api.model.bo.MateNoteBo;

@HTTPS
public interface FollowService {

    @POST("/follow/addFollow")
    Observable<ResponseBean<Void>> addFollow(@Body RequestBean<Long> requestBean);

    @POST("/follow/cancelFollow")
    Observable<ResponseBean<MateNoteBo>> cancelFollow(@Body RequestBean<Long> requestBean);

    @POST("/follow/isFollow")
    Observable<ResponseBean<Boolean>> isFollow(@Body RequestBean<Long> requestBean);

    @POST("/follow/getAttentionList")
    Observable<ResponseBean<List<AttentionBo>>> getAttentionList();

    @POST("/follow/getFansList")
    Observable<ResponseBean<List<AttentionBo>>> getFansList();

}
