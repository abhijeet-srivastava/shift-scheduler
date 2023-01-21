package org.example;

public class DepartmentSchedule {
    private Integer start;
    private Integer end;
    private String departments;

    public DepartmentSchedule(Integer start, Integer end, String departments) {
        this.start = start;
        this.end = end;
        this.departments = departments;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    public String getDepartments() {
        return departments;
    }
}
