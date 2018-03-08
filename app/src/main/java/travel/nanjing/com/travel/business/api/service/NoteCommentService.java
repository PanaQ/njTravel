package travel.nanjing.com.travel.business.api.service;

import com.zhexinit.ov.common.bean.RequestBean;
import com.zhexinit.ov.common.bean.ResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import travel.nanjing.com.travel.business.api.model.bo.AddNoteCommentBo;

public interface NoteCommentService {

    /**
     * 发表留言
     *
     * @param param
     * @return
     */
    @POST("/noteComment/addNoteComment")
    Observable<ResponseBean<Void>> addFollow(@Body RequestBean<AddNoteCommentBo> param);

}
