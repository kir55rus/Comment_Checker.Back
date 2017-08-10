package pro.batalin.commentchecker.rest.models.db.repositories;

import org.springframework.data.repository.CrudRepository;
import pro.batalin.commentchecker.rest.models.db.entitites.SearchQuery;

/**
 * @author Kirill Batalin (kir55rus)
 */
public interface SearchQueryRepository extends CrudRepository<SearchQuery, Long> {
}
