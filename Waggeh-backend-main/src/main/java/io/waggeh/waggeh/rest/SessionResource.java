package io.waggeh.waggeh.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.waggeh.waggeh.model.SessionDTO;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.model.responses.SessionInfo;
import io.waggeh.waggeh.service.SessionService;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SessionResource {

    private final SessionService sessionService;

    public SessionResource(final SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<ApiResponseWrapper<SessionDTO>> createSession(@RequestBody SessionDTO sessionDTO) {
        ApiResponseWrapper<SessionDTO> response = sessionService.createSession(sessionDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cancel")
    public ResponseEntity<ApiResponseWrapper<Object>> cancelSession(@RequestParam String sessionId) {
        ApiResponseWrapper<Object> response = sessionService.cancelSession(sessionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseWrapper<List<SessionInfo>>> getAllSessions() {
        ApiResponseWrapper<List<SessionInfo>> response = sessionService.getAllSessions();
        return ResponseEntity.ok(response);
    }


}
