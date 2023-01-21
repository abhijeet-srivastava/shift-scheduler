package org.example;

public class Shift {

    private Integer empId;

    private Integer start;
    private Integer end;

    public Shift(Integer id, Integer start, Integer end) {
        this.empId = id;
        this.start = start;
        this.end = end;
    }

    public Integer getEmpId() {
        return empId;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }
}
