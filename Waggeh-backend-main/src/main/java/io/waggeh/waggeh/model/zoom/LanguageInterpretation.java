package io.waggeh.waggeh.model.zoom;

import java.util.ArrayList;
import java.util.List;

public class LanguageInterpretation {
    private Boolean enable;
    private List<Interpreter> interpreters = new ArrayList<Interpreter>();

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Interpreter> getInterpreters() {
        return interpreters;
    }

    public void setInterpreters(List<Interpreter> interpreters) {
        this.interpreters = interpreters;
    }
}
