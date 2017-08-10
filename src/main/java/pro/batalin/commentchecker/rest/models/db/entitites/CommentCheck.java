package pro.batalin.commentchecker.rest.models.db.entitites;

import pro.batalin.commentchecker.rest.models.CheckType;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Entity
@Table(name = "comment_check")
public class CommentCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_time", nullable = false)
    private Timestamp checkTime;

    @Column(name = "result", nullable = false)
    @Enumerated(EnumType.STRING)
    private CheckType result;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @Column(name = "percent", nullable = false)
    private Integer percent;

    public CommentCheck() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    public CheckType getResult() {
        return result;
    }

    public void setResult(CheckType result) {
        this.result = result;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }
}
