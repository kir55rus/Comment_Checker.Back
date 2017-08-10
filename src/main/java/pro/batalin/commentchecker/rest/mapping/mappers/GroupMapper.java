package pro.batalin.commentchecker.rest.mapping.mappers;

import org.mapstruct.Mapper;
import pro.batalin.commentchecker.rest.mapping.dto.GroupDto;
import pro.batalin.commentchecker.rest.models.db.entitites.Group;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDto toDto(Group group);

    Iterable<GroupDto> toDto(Iterable<Group> groups);

    Group toInner(GroupDto groupDto);
}
