package pro.batalin.commentchecker.rest.models.db.repositories;

import org.springframework.data.repository.CrudRepository;
import pro.batalin.commentchecker.rest.models.db.entitites.Settings;

import java.util.List;

/**
 * @author Kirill Batalin (kir55rus)
 */
public interface SettingsRepository extends CrudRepository<Settings, Integer> {
    List<Settings> findAllByName(String name);
}
