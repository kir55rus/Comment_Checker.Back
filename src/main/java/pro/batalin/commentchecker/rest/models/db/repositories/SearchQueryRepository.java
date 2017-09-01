package pro.batalin.commentchecker.rest.models.db.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pro.batalin.commentchecker.rest.models.db.entitites.Group;
import pro.batalin.commentchecker.rest.models.db.entitites.SearchQuery;

import java.util.List;

/**
 * @author Kirill Batalin (kir55rus)
 */
public interface SearchQueryRepository extends PagingAndSortingRepository<SearchQuery, Long> {
    Page<SearchQuery> findAllByGroup(Group group, Pageable pageable);
}
