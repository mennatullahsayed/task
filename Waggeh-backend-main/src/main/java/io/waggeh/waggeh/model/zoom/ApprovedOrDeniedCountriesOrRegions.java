package io.waggeh.waggeh.model.zoom;


import java.util.ArrayList;
import java.util.List;

public class ApprovedOrDeniedCountriesOrRegions {
    private List<String> approvedList = new ArrayList<String>();
    private List<String> deniedList = new ArrayList<String>();
    private Boolean enable;
    private String method;
    public List<String> getApprovedList() {
        return approvedList;
    }
    public void setApprovedList(List<String> approvedList) {
        this.approvedList = approvedList;
    }
    public List<String> getDeniedList() {
        return deniedList;
    }
    public void setDeniedList(List<String> deniedList) {
        this.deniedList = deniedList;
    }
    public Boolean getEnable() {
        return enable;
    }
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
}



