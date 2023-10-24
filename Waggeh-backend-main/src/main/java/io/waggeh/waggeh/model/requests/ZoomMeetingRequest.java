package io.waggeh.waggeh.model.requests;

public class ZoomMeetingRequest {
    private String topic;
    private String startTime;

    public ZoomMeetingRequest() {
    }

    public ZoomMeetingRequest(String topic, String startTime) {
        this.topic = topic;
        this.startTime = startTime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
