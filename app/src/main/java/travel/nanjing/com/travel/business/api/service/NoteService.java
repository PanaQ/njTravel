package travel.nanjing.com.travel.business.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import travel.nanjing.com.travel.business.api.helper.HTTPS;
import travel.nanjing.com.travel.business.api.model.bo.BaseNoteBo;
import travel.nanjing.com.travel.business.api.model.bo.NoteBo;

@HTTPS
public interface NoteService {

    @POST("/note/addNote")
    Observable<ResponseBean<Void>> addNote(@Body RequestBean<BaseNoteBo> requestBean);

    @Multipart
    @POST("/note/uploadPicture")
    Observable<ResponseBean<String>> uploadPicture(@Part MultipartBody.Part file, @Part("picture") RequestBody description);

    @Multipart
    @POST("/note/uploadVideo")
    Observable<ResponseBean<String>> uploadVideo(@Part MultipartBody.Part file, @Part("video") RequestBody description);

    @POST("/note/deleteNote")
    Observable<ResponseBean<Void>> deleteNote(@Body RequestBean<Long> requestBean);

    @POST("/note/modifyNote")
    Observable<ResponseBean<Void>> modifyNote(@Body RequestBean<BaseNoteBo> requestBean);

    @POST("/note/getNoteById")
    Observable<ResponseBean<NoteBo>> getNoteById(@Body RequestBean<Long> requestBean);

    /**
     * 根据userId获取游记列表(获取别人的)
     */
    @POST("/note/getNoteListByUserId")
    Observable<ResponseBean<List<BaseNoteBo>>> getNoteListByUserId(@Body RequestBean<Long> requestBean);

    /**
     * 获取自己的所有游记列表
     */
    @POST("/note/getNoteListByMine")
    Observable<ResponseBean<List<BaseNoteBo>>> getNoteListByMine();

    /**
     * 获取所有的游记列表
     */
    @POST("/note/getNoteList")
    Observable<ResponseBean<List<BaseNoteBo>>> getNoteList();

}
