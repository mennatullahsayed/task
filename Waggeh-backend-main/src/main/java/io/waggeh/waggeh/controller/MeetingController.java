package io.waggeh.waggeh.controller;

//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.calendar.Calendar;
//import com.google.api.services.calendar.CalendarScopes;
//import com.google.api.services.calendar.model.Event;
//import com.google.api.services.calendar.model.EventAttendee;
//import com.google.api.services.calendar.model.EventDateTime;
import io.waggeh.waggeh.model.requests.MeetingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.ZoneId;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/api/meeting")
public class MeetingController {
//
//    private static final String APPLICATION_NAME = "waggeh meetings";
//    private static final String API_KEY = "AIzaSyBZs1pzAXIa3EiKmXCb7eTF6ajDu7MIl3Q";
//    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//
//    @PostMapping("/create")
//    public ResponseEntity<String> createMeeting(@RequestBody MeetingRequest request) {
//        try {
//            String meetingLink = createGoogleMeeting(request);
//
//            return ResponseEntity.ok("Meeting created successfully. Join URL: " + meetingLink);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error occurred while creating the meeting: " + e.getMessage());
//        }
//    }
//
//    private String createGoogleMeeting(MeetingRequest request) throws IOException, GeneralSecurityException {
//        // Implement the logic to create a Google Meeting using the Google Calendar API with API key authentication
//        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
//        Calendar calendar = new Calendar.Builder(httpTransport, JSON_FACTORY, null)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//
//        Event event = new Event()
//                .setSummary(request.getTitle())
//                .setDescription(request.getDescription());
//
//        java.util.Calendar startDateTime = java.util.Calendar.getInstance();
//        startDateTime.setTime(request.getStartTime());
//        startDateTime.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Africa/Cairo")));
//        EventDateTime start = new EventDateTime()
//                .setDateTime(new com.google.api.client.util.DateTime(startDateTime.getTime()))
//                .setTimeZone("Africa/Cairo");
//        event.setStart(start);
//
//        java.util.Calendar endDateTime = java.util.Calendar.getInstance();
//        endDateTime.setTime(request.getEndTime());
//        endDateTime.setTimeZone(TimeZone.getTimeZone(ZoneId.of("Africa/Cairo")));
//        EventDateTime end = new EventDateTime()
//                .setDateTime(new com.google.api.client.util.DateTime(endDateTime.getTime()))
//                .setTimeZone("Africa/Cairo");
//        event.setEnd(end);
//
//        List<String> attendees = request.getAttendees();
//        if (attendees != null && !attendees.isEmpty()) {
//            EventAttendee[] attendeesArray = new EventAttendee[attendees.size()];
//            for (int i = 0; i < attendees.size(); i++) {
//                attendeesArray[i] = new EventAttendee().setEmail(attendees.get(i));
//            }
//            event.setAttendees(List.of(attendeesArray));
//        }
//
//        String calendarId = "ihab.tawffuq@gmail.com";
//        Calendar.Events.Insert insertRequest = calendar.events().insert(calendarId, event);
//        insertRequest.setKey(API_KEY);
//        event = insertRequest.execute();
//
//        return event.getHangoutLink();
//    }
}
