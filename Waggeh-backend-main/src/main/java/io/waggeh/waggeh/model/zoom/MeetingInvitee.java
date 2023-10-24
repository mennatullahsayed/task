package io.waggeh.waggeh.model.zoom;

public class MeetingInvitee {
    private String email;

    public MeetingInvitee() {
    }

    public MeetingInvitee(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
