package io.waggeh.waggeh.controller;
import io.waggeh.waggeh.model.requests.ZoomMeetingRequest;
import io.waggeh.waggeh.model.responses.ApiResponseWrapper;

import io.waggeh.waggeh.service.ZoomMeetingService;

import org.springframework.http.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/zoom/")
public class ZoomMeetingController {

    private final ZoomMeetingService zoomMeetingService;

    public ZoomMeetingController(ZoomMeetingService zoomMeetingService) {
        this.zoomMeetingService = zoomMeetingService;
    }

    @PostMapping("/create-meeting")
    public ResponseEntity<ApiResponseWrapper<Map<String,String>>> createMeeting(@RequestBody ZoomMeetingRequest meetingRequest) {
        return ResponseEntity.ok(zoomMeetingService.creatMeeting(meetingRequest));

    }
}
