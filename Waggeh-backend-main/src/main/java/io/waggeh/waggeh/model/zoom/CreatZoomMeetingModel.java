package io.waggeh.waggeh.model.zoom;

import java.util.ArrayList;
import java.util.List;

public class CreatZoomMeetingModel {
    private String agenda;
    private Boolean defaultPassword;
    private Integer duration;
    private String password;
    private Boolean preSchedule;
    private Recurrence recurrence;
    private String scheduleFor;
    private Settings settings;
    private String startTime;
    private String templateId;
    private String timezone;
    private String topic;
    private List<TrackingField> trackingFields = new ArrayList<TrackingField>();
    private Integer type;

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public Boolean getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(Boolean defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPreSchedule() {
        return preSchedule;
    }

    public void setPreSchedule(Boolean preSchedule) {
        this.preSchedule = preSchedule;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public String getScheduleFor() {
        return scheduleFor;
    }

    public void setScheduleFor(String scheduleFor) {
        this.scheduleFor = scheduleFor;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<TrackingField> getTrackingFields() {
        return trackingFields;
    }

    public void setTrackingFields(List<TrackingField> trackingFields) {
        this.trackingFields = trackingFields;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
