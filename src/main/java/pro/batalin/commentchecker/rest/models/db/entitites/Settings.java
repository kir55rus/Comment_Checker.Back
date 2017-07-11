package pro.batalin.commentchecker.rest.models.db.entitites;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Entity
@Table(name = "settings")
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Length(min = 1, max = 250)
    private String name;

    private String value;

    public Settings() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
