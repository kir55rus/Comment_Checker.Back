package pro.batalin.commentchecker.rest.mapping.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Length;

/**
 * @author Kirill Batalin (kir55rus)
 */
public class GroupDto {
    private Integer id;
    @Length(min = 3, max = 200)
    private String name;

    public GroupDto() {
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
