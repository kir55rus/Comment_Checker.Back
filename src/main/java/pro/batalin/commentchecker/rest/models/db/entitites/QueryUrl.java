package pro.batalin.commentchecker.rest.models.db.entitites;

import pro.batalin.commentchecker.rest.models.SearchEngine;

import javax.persistence.*;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Entity
@Table(name = "queries_urls")
public class QueryUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "search_query_id", nullable = false)
    private SearchQuery searchQuery;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "url_id", nullable = false)
    private Url url;

    @Column(name = "search_engine", nullable = false)
    @Enumerated(EnumType.STRING)
    private SearchEngine searchEngine;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "weight", nullable = false)
    private Long weight;

    public QueryUrl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SearchQuery getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public SearchEngine getSearchEngine() {
        return searchEngine;
    }

    public void setSearchEngine(SearchEngine searchEngine) {
        this.searchEngine = searchEngine;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }
}
