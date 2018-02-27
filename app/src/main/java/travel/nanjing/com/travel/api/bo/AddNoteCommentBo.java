package travel.nanjing.com.travel.api.bo;



import javax.validation.constraints.NotNull;

public class AddNoteCommentBo {

    /**
     * 游记id
     */
    @NotNull(message = "游记id不能为空")
    private Long noteId;

    /**
     * 留言内容
     */
    private String comment;

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }



}