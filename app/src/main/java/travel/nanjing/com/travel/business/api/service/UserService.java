package travel.nanjing.com.travel.business.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import travel.nanjing.com.travel.business.api.helper.HTTPS;
import travel.nanjing.com.travel.business.api.model.bo.BaseUserBo;
import travel.nanjing.com.travel.business.api.model.bo.LoginBean;
import travel.nanjing.com.travel.business.api.model.bo.UserBo;

@HTTPS
public interface UserService {

    /**
     * 登录
     * @param requestBean
     * @return
     */
    @POST("/user/login")
    Observable<ResponseBean<Void>> login(@Body RequestBean<LoginBean> requestBean);

    /**
     * 退出
     * @return
     */
    @POST("/user/logout")
    @Headers("X-Auth-Token")
    Observable<Void> logout();

    /**
     * 用户注册
     * @param requestBean
     * @return
     */
    @POST("/user/register")
    Observable<ResponseBean<Void>> register(@Body RequestBean<BaseUserBo> requestBean);

    /**
     * 获取我的个人信息
     * @param requestBean
     * @return
     */
    @POST("/user/getMyInfo")
    Observable<ResponseBean<UserBo>> getMyInfo(@Body RequestBean<Void> requestBean);

    /**
     * 修改我的个人信息
     * @param requestBean
     * @return
     */
    @POST("/user/updateMyInfo")
    Observable<ResponseBean<UserBo>> updateMyInfo(@Body RequestBean<UserBo> requestBean);


}
