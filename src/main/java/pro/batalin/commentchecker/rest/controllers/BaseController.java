package pro.batalin.commentchecker.rest.controllers;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pro.batalin.commentchecker.rest.mapping.dto.ErrorDto;
import pro.batalin.commentchecker.rest.mapping.dto.ErrorsDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

/**
 * @author Kirill Batalin (kir55rus)
 */
public class BaseController {
    private final static Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(DataAccessException.class)
    ResponseEntity<ErrorsDto> onDbError(HttpServletRequest req, DataAccessException ex) {
        if (logger.isErrorEnabled()) {
            logger.error("Db error: {}", ex);
        }
        ErrorsDto errorsDto = new ErrorsDto();
        errorsDto.setErrors(Collections.singletonList(new ErrorDto("Database error")));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorsDto);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorsDto> onException(HttpServletRequest req, Exception ex) {
        if (logger.isErrorEnabled()) {
            logger.error("Exception: {}", ex);
        }
        ErrorsDto errorsDto = new ErrorsDto();
        errorsDto.setErrors(Collections.singletonList(new ErrorDto("Internal server error")));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorsDto);
    }
}
