package io.waggeh.waggeh.model.zoom;

import java.util.ArrayList;
import java.util.List;

public class SignLanguageInterpretation {
    private Boolean enable;
    private List<Interpreter_> interpreters = new ArrayList<Interpreter_>();

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public List<Interpreter_> getInterpreters() {
        return interpreters;
    }

    public void setInterpreters(List<Interpreter_> interpreters) {
        this.interpreters = interpreters;
    }
}
