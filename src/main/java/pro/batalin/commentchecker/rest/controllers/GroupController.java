package pro.batalin.commentchecker.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pro.batalin.commentchecker.rest.mapping.dto.GroupDto;
import pro.batalin.commentchecker.rest.mapping.mappers.ErrorMapper;
import pro.batalin.commentchecker.rest.mapping.mappers.GroupMapper;
import pro.batalin.commentchecker.rest.models.db.entitites.Group;
import pro.batalin.commentchecker.rest.models.db.repositories.GroupRepository;

import javax.validation.Valid;

/**
 * @author Kirill Batalin (kir55rus)
 */

@RestController
@RequestMapping(path = "rest/groups")
public class GroupController extends BaseController {

    private final GroupRepository groupRepository;
    private final ErrorMapper errorMapper;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupController(GroupRepository groupRepository,
                           ErrorMapper errorMapper,
                           GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.errorMapper = errorMapper;
        this.groupMapper = groupMapper;
    }

    @RequestMapping(path = "", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> getGroups() {
        return ResponseEntity.ok(groupMapper.toDto(groupRepository.findAll()));
    }

    @RequestMapping(path = "", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createGroup(@Valid @RequestBody GroupDto group,
                                                   Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(errors);
        }

        Group newGroup = groupRepository.save(groupMapper.toInner(group));
        return ResponseEntity.ok(newGroup);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void delGroup(@PathVariable int id) {
        groupRepository.delete(id);
    }
}
