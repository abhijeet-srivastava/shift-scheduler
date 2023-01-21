package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShiftSchedulerTest {

    ShiftScheduler shiftScheduler = new ShiftScheduler();
    @org.junit.jupiter.api.Test
    void fetchEmployeeSchedule() {
        List<DepartmentSchedule> deptSchedules = new ArrayList<>();
        deptSchedules.add(new DepartmentSchedule(9, 10, "A"));
        deptSchedules.add(new DepartmentSchedule(10, 12, "B"));
        deptSchedules.add(new DepartmentSchedule(14, 15, "C"));
        deptSchedules.add(new DepartmentSchedule(15, 18, "D"));
        List<Shift> empShifts = shiftScheduler.fetchEmployeeSchedule(1, deptSchedules);
        assertEquals(2, empShifts.size());
        List<Shift> expected = new ArrayList<>();
        expected.add(new Shift(1, 9, 12));
        expected.add(new Shift(1, 14, 18));
        assertEquals(9, empShifts.get(0).getStart());
        assertEquals(12, empShifts.get(0).getEnd());

        assertEquals(14, empShifts.get(1).getStart());
        assertEquals(18, empShifts.get(1).getEnd());
    }

    @org.junit.jupiter.api.Test
    void fetchEmployeeScheduleEmpty() {
        List<Shift> empShifts = shiftScheduler.fetchEmployeeSchedule(1, Collections.EMPTY_LIST);
        assertEquals(0, empShifts.size());
    }

}