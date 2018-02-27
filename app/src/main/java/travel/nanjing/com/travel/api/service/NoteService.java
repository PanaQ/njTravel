package travel.nanjing.com.travel.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;
import com.zhexinit.ov.common.query.ListBean;
import com.zhexinit.ov.common.query.SortPagerQuery;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import travel.nanjing.com.travel.api.bo.BaseNoteBo;
import travel.nanjing.com.travel.api.bo.NoteBo;
import travel.nanjing.com.travel.api.bo.NoteQuery;
import travel.nanjing.com.travel.api.helper.HTTPS;

@HTTPS
public interface NoteService {

    @POST("/note/addNote")
    Observable<ResponseBean<Void>> addNote(@Body RequestBean<BaseNoteBo> requestBean);

    @Multipart
    @POST("/note/uploadPicture")
    Observable<ResponseBean<Void>> uploadPicture(@Part("picture") MultipartBody.Part file);

    @Multipart
    @POST("/note/uploadVideo")
    Observable<ResponseBean<Void>> uploadVideo(@Part("video") MultipartBody.Part file);

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
    Observable<ResponseBean<ListBean<BaseNoteBo>>> getNoteListByUserId(@Body RequestBean<SortPagerQuery<NoteQuery>> requestBean);

    /**
     * 获取自己的所有游记列表
     */
    @POST("/note/getNoteListByMine")
    Observable<ResponseBean<ListBean<BaseNoteBo>>> getNoteListByMine(@Body RequestBean<SortPagerQuery> requestBean);

    /**
     * 获取所有的游记列表
     */
    @POST("/note/getNoteList")
    Observable<ResponseBean<ListBean<BaseNoteBo>>> getNoteList(@Body RequestBean<SortPagerQuery> requestBean);

}
