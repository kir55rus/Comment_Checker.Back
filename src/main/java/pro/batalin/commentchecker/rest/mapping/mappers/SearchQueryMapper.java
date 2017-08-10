package pro.batalin.commentchecker.rest.mapping.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import pro.batalin.commentchecker.rest.mapping.dto.SearchQueryDto;
import pro.batalin.commentchecker.rest.models.db.entitites.SearchQuery;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Mapper(componentModel = "spring")
public interface SearchQueryMapper {
    @Mapping(target = "groupId", source = "group.id")
    SearchQueryDto toDto(SearchQuery searchQuery);

    Iterable<SearchQueryDto> toDto(Iterable<SearchQuery> searchQueries);

    @Mappings({
            @Mapping(target = "group.id", source = "groupId"),
            @Mapping(target = "queryUrls", ignore = true)
    })
    SearchQuery toInner(SearchQueryDto searchQueryDto);
}
