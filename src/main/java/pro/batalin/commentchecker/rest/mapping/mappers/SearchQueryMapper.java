package pro.batalin.commentchecker.rest.mapping.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pro.batalin.commentchecker.rest.mapping.dto.SearchQueryDto;
import pro.batalin.commentchecker.rest.models.db.entitites.SearchQuery;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Mapper(componentModel = "spring", uses = {GroupMapper.class})
public interface SearchQueryMapper {
    SearchQueryDto toDto(SearchQuery searchQuery);

    Iterable<SearchQueryDto> toDto(Iterable<SearchQuery> searchQueries);

    @Mappings({
            @Mapping(target = "queryUrls", ignore = true),
            @Mapping(target = "yandexChecked", defaultValue = "false"),
            @Mapping(target = "googleChecked", defaultValue = "false"),
    })
    SearchQuery toInner(SearchQueryDto searchQueryDto);
}
