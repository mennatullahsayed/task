package io.waggeh.waggeh.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.waggeh.waggeh.model.ScheduleSlotDTO;
import io.waggeh.waggeh.model.requests.CreateConsultantSchedule;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;
import io.waggeh.waggeh.service.ScheduleSlotService;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/api/scheduleSlots", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ScheduleSlotResource {

    private final ScheduleSlotService scheduleSlotService;

    public ScheduleSlotResource(final ScheduleSlotService scheduleSlotService) {
        this.scheduleSlotService = scheduleSlotService;
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<ApiResponseWrapper> createScheduleSlot(
            @RequestBody CreateConsultantSchedule scheduleSlots){

        ApiResponseWrapper response = scheduleSlotService.createSlots(scheduleSlots);
        return  ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiResponse(responseCode = "200")
    public ResponseEntity<ApiResponseWrapper<List<ScheduleSlotDTO>>> getAllConsultantSlots(@RequestParam("consultantId") String consultantId){
        ApiResponseWrapper<List<ScheduleSlotDTO>> response = scheduleSlotService.getAllFreeSlots(consultantId);
        return  ResponseEntity.ok(response);
    }






}
