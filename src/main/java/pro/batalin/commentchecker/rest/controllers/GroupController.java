package pro.batalin.commentchecker.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pro.batalin.commentchecker.rest.models.ApiResponse;
import pro.batalin.commentchecker.rest.models.db.entitites.Group;
import pro.batalin.commentchecker.rest.models.db.repositories.GroupRepository;

import javax.validation.Valid;

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
    public ResponseEntity<ApiResponse> getGroups() {
        try {
            return ResponseEntity.ok(ApiResponse.withData(groupRepository.findAll()));
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body(ApiResponse.withError(e.getLocalizedMessage()));
        }
    }

    @RequestMapping(path = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ApiResponse> createGroup(@Valid @RequestBody Group group, Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().toString();
            return ResponseEntity.badRequest().body(ApiResponse.withError(errorMessage));
        }

        try {
            Group newGroup = groupRepository.save(group);
            return ResponseEntity.ok(ApiResponse.withData(newGroup));
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body(ApiResponse.withError(e.getLocalizedMessage()));
        }
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ApiResponse> delGroup(@PathVariable int id) {
        try {
            groupRepository.delete(id);
            return ResponseEntity.ok(ApiResponse.withData(null));
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body(ApiResponse.withError(e.getLocalizedMessage()));
        }
    }
}
