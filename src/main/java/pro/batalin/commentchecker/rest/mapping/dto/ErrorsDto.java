package pro.batalin.commentchecker.rest.mapping.dto;

import java.util.List;

/**
 * @author Kirill Batalin (kir55rus)
 */
public class ErrorsDto {
    private List<ErrorDto> errors;

    public ErrorsDto() {
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }
}
