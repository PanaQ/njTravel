package travel.nanjing.com.travel.business.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import travel.nanjing.com.travel.business.api.model.bo.DivisionBo;

public interface DevisionService {

    /**
     * 查询省份列表
     * @param param
     * @return
     */
    @GET("/division/selectProvinces")
    Observable<ResponseBean<List<DivisionBo>>> selectProvinces(@Body RequestBean<Void> param);

    /**
     * 查询省市区
     * @param parentCode
     * @return
     */
    @GET("/division/selectByParentCode")
    Observable<ResponseBean<List<DivisionBo>>> selectByParentCode(@Query("parentCode") String parentCode);

    /**
     * 查询
     * @param code
     * @return
     */
    @GET("/division/selectByCode")
    Observable<ResponseBean<List<DivisionBo>>> selectByCode(@Query("code") String code);

}
