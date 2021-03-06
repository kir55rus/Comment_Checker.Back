package pro.batalin.commentchecker.rest.models.db.entitites;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Entity
@Table(name = "query_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 3, max = 200)
    @Column(name = "name", nullable = false)
    private String name;

    public Group() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
