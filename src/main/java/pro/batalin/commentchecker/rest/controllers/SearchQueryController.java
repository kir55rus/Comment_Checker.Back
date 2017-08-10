package pro.batalin.commentchecker.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pro.batalin.commentchecker.rest.mapping.dto.SearchQueryDto;
import pro.batalin.commentchecker.rest.mapping.mappers.ErrorMapper;
import pro.batalin.commentchecker.rest.mapping.mappers.SearchQueryMapper;
import pro.batalin.commentchecker.rest.models.db.entitites.SearchQuery;
import pro.batalin.commentchecker.rest.models.db.repositories.SearchQueryRepository;

import javax.validation.Valid;

/**
 * @author Kirill Batalin (kir55rus)
 */
@RestController
@RequestMapping(path = "rest/search-queries")
public class SearchQueryController extends BaseController {
    private final SearchQueryRepository searchQueryRepository;
    private final ErrorMapper errorMapper;
    private final SearchQueryMapper searchQueryMapper;

    @Autowired
    public SearchQueryController(SearchQueryRepository searchQueryRepository,
                                 ErrorMapper errorMapper,
                                 SearchQueryMapper searchQueryMapper) {
        this.searchQueryRepository = searchQueryRepository;
        this.errorMapper = errorMapper;
        this.searchQueryMapper = searchQueryMapper;
    }

    @RequestMapping(path = "", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(searchQueryMapper.toDto(searchQueryRepository.findAll()));
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
