package travel.nanjing.com.travel.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;

import retrofit2.http.Body;
import retrofit2.http.POST;
import io.reactivex.Observable;
import travel.nanjing.com.travel.api.bo.MateNoteBo;
import travel.nanjing.com.travel.api.bo.UserFollowBo;
import travel.nanjing.com.travel.api.helper.HTTPS;

@HTTPS
public interface FollowService {

    @POST("/follow/addFollow")
    Observable<ResponseBean<Void>> addFollow(@Body RequestBean<UserFollowBo> requestBean);

    @POST("/follow/cancelFollow")
    Observable<ResponseBean<MateNoteBo>> cancelFollow(@Body RequestBean<UserFollowBo> requestBean);

}
