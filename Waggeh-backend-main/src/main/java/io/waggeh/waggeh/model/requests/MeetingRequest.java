package io.waggeh.waggeh.model.requests;
import java.util.Date;
import java.util.List;

public class MeetingRequest {
    private String title;
    private String description;
    private Date startTime;
    private Date endTime;
    private List<String> attendees;

    // Constructors, getters, and setters

    public MeetingRequest() {
    }

    public MeetingRequest(String title, String description, Date startTime, Date endTime, List<String> attendees) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attendees = attendees;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }
}
