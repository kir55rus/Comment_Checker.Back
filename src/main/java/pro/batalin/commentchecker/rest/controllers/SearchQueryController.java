package pro.batalin.commentchecker.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pro.batalin.commentchecker.rest.mapping.dto.SearchQueryDto;
import pro.batalin.commentchecker.rest.mapping.mappers.ErrorMapper;
import pro.batalin.commentchecker.rest.mapping.mappers.SearchQueryMapper;
import pro.batalin.commentchecker.rest.models.db.entitites.Group;
import pro.batalin.commentchecker.rest.models.db.entitites.SearchQuery;
import pro.batalin.commentchecker.rest.models.db.repositories.GroupRepository;
import pro.batalin.commentchecker.rest.models.db.repositories.SearchQueryRepository;
import pro.batalin.commentchecker.rest.utils.PaginationUtils;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * @author Kirill Batalin (kir55rus)
 */
@RestController
@RequestMapping(path = "rest/search-queries")
public class SearchQueryController extends BaseController {
    private final SearchQueryRepository searchQueryRepository;
    private final ErrorMapper errorMapper;
    private final SearchQueryMapper searchQueryMapper;
    private final GroupRepository groupRepository;

    @Autowired
    public SearchQueryController(SearchQueryRepository searchQueryRepository,
                                 ErrorMapper errorMapper,
                                 SearchQueryMapper searchQueryMapper,
                                 GroupRepository groupRepository) {
        this.searchQueryRepository = searchQueryRepository;
        this.errorMapper = errorMapper;
        this.searchQueryMapper = searchQueryMapper;
        this.groupRepository = groupRepository;
    }

    @RequestMapping(path = "{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> getSearchQuery(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(searchQueryMapper.toDto(searchQueryRepository.findOne(id)));
    }

    @RequestMapping(path = "", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<SearchQuery> searchQueries = searchQueryRepository.findAll(pageable);
        return ResponseEntity.ok()
                .headers(PaginationUtils.generatePaginationHttpHeaders(
                        "/rest/search-queries",
                        searchQueries,
                        pageable))
                .body(searchQueries.getContent()
                        .stream()
                        .map(searchQueryMapper::toDto)
                        .collect(Collectors.toList()));
    }

    @RequestMapping(path = "group/{group_id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> getAll(@PathVariable(name = "group_id") Integer groupId, Pageable pageable) {
        Group group = groupRepository.findOne(groupId);
        if (group == null) {
            return ResponseEntity.notFound().build();
        }

        Page<SearchQuery> searchQueries = searchQueryRepository.findAllByGroup(group, pageable);
        return ResponseEntity.ok()
                .headers(PaginationUtils.generatePaginationHttpHeaders(
                        String.format("/rest/search-queries/group/%d", group.getId()),
                        searchQueries,
                        pageable))
                .body(searchQueries.getContent()
                        .stream()
                        .map(searchQueryMapper::toDto)
                        .collect(Collectors.toList()));
    }

    @RequestMapping(path = "", method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createSearchQuery(@Valid @RequestBody SearchQueryDto searchQuery,
                                               Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(errorMapper.toDto(errors));
        }

        SearchQuery query = searchQueryRepository.save(searchQueryMapper.toInner(searchQuery));
        return ResponseEntity.ok(query);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public void delSearchQuery(@PathVariable long id) {
        searchQueryRepository.delete(id);
    }
}
