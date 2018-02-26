package travel.nanjing.com.travel.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;
import com.zhexinit.ov.common.query.PagerQuery;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import travel.nanjing.com.travel.api.bo.BaseNoteBo;
import travel.nanjing.com.travel.api.bo.NoteBo;
import travel.nanjing.com.travel.api.helper.HTTPS;

@HTTPS
public interface NoteService {

    @POST("/note/addNote")
    Observable<ResponseBean<Void>> addNote(@Body RequestBean<BaseNoteBo> requestBean);

//    @POST("/note/uploadPicture")
//    Observable<ResponseBean<Void>> uploadPicture(@Param("picture") HttpServletRequest request);

//    @POST("/note/uploadVideo")
//    Observable<ResponseBean<Void>> uploadVideo(@Param("video") HttpServletRequest request);

    @POST("/note/getNoteById")
    Observable<ResponseBean<NoteBo>> getNoteById(@Body RequestBean<Long> requestBean);

    @POST("/note/getNoteList")
    Observable<ResponseBean<NoteBo>> getNoteList(@Body RequestBean<PagerQuery<Void>> requestBean);

}
