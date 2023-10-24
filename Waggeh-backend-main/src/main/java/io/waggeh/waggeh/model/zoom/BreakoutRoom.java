package io.waggeh.waggeh.model.zoom;

import java.util.ArrayList;
import java.util.List;

public class BreakoutRoom {
    private Boolean enable;
    private List<Room> rooms = new ArrayList<Room>();

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
