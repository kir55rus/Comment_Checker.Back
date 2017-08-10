package pro.batalin.commentchecker.rest.mapping.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import pro.batalin.commentchecker.rest.mapping.dto.ErrorDto;
import pro.batalin.commentchecker.rest.mapping.dto.ErrorsDto;

/**
 * @author Kirill Batalin (kir55rus)
 */
@Mapper(componentModel = "spring")
public abstract class ErrorMapper {
    public ErrorDto toDto(ObjectError error) {
        return new ErrorDto(error.toString());
    }

    public ErrorDto toDto(FieldError error) {
        return new ErrorDto(error.toString());
    }

    @Mapping(target = "errors", source = "fieldErrors")
    public abstract ErrorsDto toDto(Errors errors);
}
