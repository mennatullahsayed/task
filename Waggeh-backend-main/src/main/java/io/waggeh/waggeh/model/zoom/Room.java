package io.waggeh.waggeh.model.zoom;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    private List<String> participants = new ArrayList<String>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
}
