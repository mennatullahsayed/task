package io.waggeh.waggeh.model.zoom;

public class Recurrence {
    private String endDateTime;
    private Integer endTimes;
    private Integer monthlyDay;
    private Integer monthlyWeek;
    private Integer monthlyWeekDay;
    private Integer repeatInterval;
    private Integer type;
    private String weeklyDays;

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(Integer endTimes) {
        this.endTimes = endTimes;
    }

    public Integer getMonthlyDay() {
        return monthlyDay;
    }

    public void setMonthlyDay(Integer monthlyDay) {
        this.monthlyDay = monthlyDay;
    }

    public Integer getMonthlyWeek() {
        return monthlyWeek;
    }

    public void setMonthlyWeek(Integer monthlyWeek) {
        this.monthlyWeek = monthlyWeek;
    }

    public Integer getMonthlyWeekDay() {
        return monthlyWeekDay;
    }

    public void setMonthlyWeekDay(Integer monthlyWeekDay) {
        this.monthlyWeekDay = monthlyWeekDay;
    }

    public Integer getRepeatInterval() {
        return repeatInterval;
    }

    public void setRepeatInterval(Integer repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getWeeklyDays() {
        return weeklyDays;
    }

    public void setWeeklyDays(String weeklyDays) {
        this.weeklyDays = weeklyDays;
    }
}
