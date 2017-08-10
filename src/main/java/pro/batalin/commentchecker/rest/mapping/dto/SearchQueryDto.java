package pro.batalin.commentchecker.rest.mapping.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Kirill Batalin (kir55rus)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SearchQueryDto {
    private Long id;

    @Length(min = 1, max = 250)
    private String searchQuery;

    @Min(value = 0)
    private Long frequency;

    private Boolean yandexChecked;

    private Boolean googleChecked;

    @NotNull
    private Integer groupId;

    public SearchQueryDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public Long getFrequency() {
        return frequency;
    }

    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    public Boolean getYandexChecked() {
        return yandexChecked;
    }

    public void setYandexChecked(Boolean yandexChecked) {
        this.yandexChecked = yandexChecked;
    }

    public Boolean getGoogleChecked() {
        return googleChecked;
    }

    public void setGoogleChecked(Boolean googleChecked) {
        this.googleChecked = googleChecked;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
