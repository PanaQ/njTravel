package travel.nanjing.com.travel.api.bo;

import java.util.List;

public class NoteBo extends BaseNoteBo{

    /**
     * 用户名
     */
    private String userName;
    /**
     * 留言列表
     */
    private List<NoteCommentBo> noteCommentBoList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<NoteCommentBo> getNoteCommentBoList() {
        return noteCommentBoList;
    }

    public void setNoteCommentBoList(List<NoteCommentBo> noteCommentBoList) {
        this.noteCommentBoList = noteCommentBoList;
    }
}
