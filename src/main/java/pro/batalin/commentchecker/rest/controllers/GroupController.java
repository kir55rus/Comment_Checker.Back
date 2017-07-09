package pro.batalin.commentchecker.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pro.batalin.commentchecker.rest.models.db.entitites.Group;
import pro.batalin.commentchecker.rest.models.db.repositories.GroupRepository;

/**
 * @author Kirill Batalin (kir55rus)
 */

@RestController
@RequestMapping(path = "rest/groups")
public class GroupController {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupController(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @RequestMapping(path = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> getGroups() {
        return ResponseEntity.ok(groupRepository.findAll());
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> delGroup(@PathVariable int id) {
        try {
            groupRepository.delete(id);
            return ResponseEntity.ok().build();
        } catch (DataAccessException e) {
            return ResponseEntity.noContent().build();
        }
    }
}
