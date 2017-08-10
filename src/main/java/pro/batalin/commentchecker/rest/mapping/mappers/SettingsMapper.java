package pro.batalin.commentchecker.rest.mapping.mappers;

import org.mapstruct.Mapper;
import pro.batalin.commentchecker.rest.mapping.dto.SettingsDto;
import pro.batalin.commentchecker.rest.models.db.entitites.Settings;

import java.util.List;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Mapper(componentModel = "spring")
public interface SettingsMapper {
    SettingsDto toDto(Settings settings);

    Iterable<SettingsDto> toDto(Iterable<Settings> settings);
}
