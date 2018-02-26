package travel.nanjing.com.travel.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import io.reactivex.Observable;
import travel.nanjing.com.travel.api.bo.LoginBean;
import travel.nanjing.com.travel.api.helper.HTTPS;

@HTTPS
public interface UserLoginService {

    @POST("/user/login")
    Observable<ResponseBean<Void>> login(@Body RequestBean<LoginBean> requestBean);

    @POST("/user/register")
    Observable<ResponseBean<Void>> register(@Body RequestBean<LoginBean> requestBean);

    @POST("/user/logout")
    @Headers("X-Auth-Token")
    Observable<Void> logout();

}
