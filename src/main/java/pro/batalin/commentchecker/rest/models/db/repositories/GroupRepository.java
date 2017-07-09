package pro.batalin.commentchecker.rest.models.db.repositories;

import org.springframework.data.repository.CrudRepository;
import pro.batalin.commentchecker.rest.models.db.entitites.Group;

/**
 * @author Kirill Batalin (kir55rus)
 */
public interface GroupRepository extends CrudRepository<Group, Integer> {
}
