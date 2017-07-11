package pro.batalin.commentchecker.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pro.batalin.commentchecker.rest.models.ApiResponse;
import pro.batalin.commentchecker.rest.models.db.entitites.Settings;
import pro.batalin.commentchecker.rest.models.db.repositories.SettingsRepository;

import javax.validation.Valid;

/**
 * @author Kirill Batalin (kir55rus)
 */
@RestController
@RequestMapping(path = "rest/settings")
public class SettingsController {

    private final SettingsRepository settingsRepository;

    @Autowired
    public SettingsController(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    @RequestMapping(path = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ApiResponse> getAll() {
        try {
            return ResponseEntity.ok(ApiResponse.withData(settingsRepository.findAll()));
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body(ApiResponse.withError(e.getLocalizedMessage()));
        }
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ApiResponse> editById(
            @PathVariable int id,
            @Valid @RequestBody Settings newSettings,
            Errors errors) {
        if (errors.hasErrors()) {
            String errorMessage = errors.getFieldError().toString();
            return ResponseEntity.badRequest().body(ApiResponse.withError(errorMessage));
        }

        try {
            Settings settings = settingsRepository.findOne(id);
            settings.setName(newSettings.getName());
            settings.setValue(newSettings.getValue());
            settingsRepository.save(settings);
            return ResponseEntity.ok(ApiResponse.withData(settings));
        } catch (DataAccessException e) {
            return ResponseEntity.badRequest().body(ApiResponse.withError(e.getLocalizedMessage()));
        }
    }
}
