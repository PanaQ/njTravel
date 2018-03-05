package travel.nanjing.com.travel.business.api.model.bo;


public class NoteCommentBo {

    /**
     * 游记id
     */
    private Long noteId;

    /**
     * 留言用户id
     */
    private Long commentUserId;


    /**
     * 留言内容
     */
    private String comment;

    /**
     * 留言用户的名称
     */
    private String commentName;

    /**
     * 获取游记id
     *
     * @return note_id - 游记id
     */
    public Long getNoteId() {
        return noteId;
    }

    /**
     * 设置游记id
     *
     * @param noteId 游记id
     */
    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public Long getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(Long commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    /**
     * 获取留言内容
     *
     * @return comment - 留言内容
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置留言内容
     *
     * @param comment 留言内容
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }



}