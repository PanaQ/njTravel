package travel.nanjing.com.travel.api.bo;

public class BaseNoteBo {

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 游记内容
     */
    private String content;

    /**
     * 创建时间
     */
    private Long createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
