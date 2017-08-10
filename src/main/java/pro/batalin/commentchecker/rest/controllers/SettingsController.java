package pro.batalin.commentchecker.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pro.batalin.commentchecker.rest.mapping.dto.SettingsDto;
import pro.batalin.commentchecker.rest.mapping.mappers.ErrorMapper;
import pro.batalin.commentchecker.rest.mapping.mappers.SettingsMapper;
import pro.batalin.commentchecker.rest.models.db.entitites.Settings;
import pro.batalin.commentchecker.rest.models.db.repositories.SettingsRepository;

import javax.validation.Valid;

/**
 * @author Kirill Batalin (kir55rus)
 */
@RestController
@RequestMapping(path = "rest/settings")
public class SettingsController extends BaseController {

    private final SettingsRepository settingsRepository;
    private final ErrorMapper errorMapper;
    private final SettingsMapper settingsMapper;

    @Autowired
    public SettingsController(SettingsRepository settingsRepository,
                              ErrorMapper errorMapper,
                              SettingsMapper settingsMapper) {
        this.settingsRepository = settingsRepository;
        this.errorMapper = errorMapper;
        this.settingsMapper = settingsMapper;
    }

    @RequestMapping(path = "", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(settingsMapper.toDto(settingsRepository.findAll()));
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> editById(
            @PathVariable int id,
            @Valid @RequestBody SettingsDto newSettings,
            Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(errorMapper.toDto(errors));
        }

        Settings settings = settingsRepository.findOne(id);
        settings.setName(newSettings.getName());
        settings.setValue(newSettings.getValue());
        settingsRepository.save(settings);
        return ResponseEntity.ok(settingsMapper.toDto(settings));
    }
}
