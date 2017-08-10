package pro.batalin.commentchecker.rest.models.db.entitites;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Set;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Entity
@Table(name = "search_query")
public class SearchQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 1, max = 250)
    @Column(name = "search_query", nullable = false)
    private String searchQuery;

    @Min(value = 0)
    @Column(name = "frequency", nullable = false)
    private Long frequency;

    @Column(name = "yandex_checked", nullable = false)
    private Boolean yandexChecked;

    @Column(name = "google_checked", nullable = false)
    private Boolean googleChecked;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @OneToMany(mappedBy = "searchQuery", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<QueryUrl> queryUrls;

    public SearchQuery() {
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Set<QueryUrl> getQueryUrls() {
        return queryUrls;
    }

    public void setQueryUrls(Set<QueryUrl> queryUrls) {
        this.queryUrls = queryUrls;
    }
}
