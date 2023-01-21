package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShiftSchedulerTestOverLaps {

    ShiftScheduler shiftScheduler = new ShiftScheduler();
    @Test
    void fetchEmployeeScheduleOverLapping() {
        List<DepartmentSchedule> deptSchedules = new ArrayList<>();
        deptSchedules.add(new DepartmentSchedule(9, 14, "A"));
        deptSchedules.add(new DepartmentSchedule(10, 12, "B"));
        deptSchedules.add(new DepartmentSchedule(16, 17, "C"));
        deptSchedules.add(new DepartmentSchedule(17, 18, "D"));
        List<Shift> empShifts = shiftScheduler.fetchEmployeeScheduleOverLapping(1, deptSchedules);
        assertEquals(2, empShifts.size());
        assertEquals(9, empShifts.get(0).getStart());
        assertEquals(14, empShifts.get(0).getEnd());

        assertEquals(16, empShifts.get(1).getStart());
        assertEquals(18, empShifts.get(1).getEnd());
    }
}