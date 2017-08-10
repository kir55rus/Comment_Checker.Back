package pro.batalin.commentchecker.rest.models.db.entitites;

import pro.batalin.commentchecker.rest.models.CheckType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "comment_block", nullable = false)
    @Enumerated(EnumType.STRING)
    private CheckType commentBlock;

    @Column(name = "url_date", nullable = false)
    private Timestamp date;

    @OneToMany(mappedBy = "url", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<QueryUrl> queryUrls;

    public Url() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public CheckType getCommentBlock() {
        return commentBlock;
    }

    public void setCommentBlock(CheckType commentBlock) {
        this.commentBlock = commentBlock;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Set<QueryUrl> getQueryUrls() {
        return queryUrls;
    }

    public void setQueryUrls(Set<QueryUrl> queryUrls) {
        this.queryUrls = queryUrls;
    }
}
