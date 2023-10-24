package io.waggeh.waggeh.model.responses;

public class ZoomMeetingResponse {
    private String joinUrl;

    public ZoomMeetingResponse() {
    }

    public ZoomMeetingResponse(String joinUrl) {
        this.joinUrl = joinUrl;
    }

    public String getJoinUrl() {
        return joinUrl;
    }

    public void setJoinUrl(String joinUrl) {
        this.joinUrl = joinUrl;
    }
}
