package travel.nanjing.com.travel.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;
import com.zhexinit.ov.common.query.ListBean;
import com.zhexinit.ov.common.query.PagerQuery;
import com.zhexinit.ov.common.query.SortPagerQuery;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import travel.nanjing.com.travel.api.bo.AddMateNoteBo;
import travel.nanjing.com.travel.api.bo.MateNoteBo;
import travel.nanjing.com.travel.api.bo.MateNoteQuery;
import travel.nanjing.com.travel.api.helper.HTTPS;

@HTTPS
public interface MateNoteService {

    @POST("/mateNote/addMateNote")
    Observable<ResponseBean<Void>> addMateNote(@Body RequestBean<AddMateNoteBo> requestBean);

    @POST("/mateNote/deleteMateNote")
    Observable<ResponseBean<Void>> deleteMateNote(@Body RequestBean<Long> requestBean);

    @POST("/mateNote/modifyMateNote")
    Observable<ResponseBean<Void>> modifyMateNote(@Body RequestBean<MateNoteBo> requestBean);

    @POST("/mateNote/getMateNoteById")
    Observable<ResponseBean<MateNoteBo>> getMateNoteById(@Body RequestBean<Long> requestBean);

    /**
     * 根据userId获取结伴游列表(获取别人的)
     */
    @POST("/mateNote/getMateNoteListByUserId")
    Observable<ResponseBean<ListBean<MateNoteBo>>> getMateNoteListByUserId(@Body RequestBean<Long> requestBean);

    /**
     * 获取自己的所有结伴游列表
     */
    @POST("/mateNote/getNoteListByMine")
    Observable<ResponseBean<ListBean<MateNoteBo>>> getMateNoteListByMine(@Body RequestBean<SortPagerQuery> requestBean);

    /**
     * 获取所有的结伴游列表
     */
    @POST("/mateNote/getMateNoteList")
    Observable<ResponseBean<ListBean<MateNoteBo>>> getMateNoteList(@Body RequestBean<Long> requestBean);

}
