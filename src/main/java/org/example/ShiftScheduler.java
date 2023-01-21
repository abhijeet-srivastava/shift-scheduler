package org.example;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class ShiftScheduler {

    //10 - 15; 11 - 16; 16 - 18; 18-22 -
    // s 16  e 18 => curr 16, 18
    public List<Shift> fetchEmployeeSchedule(Integer empId, List<DepartmentSchedule> departments) {
        List<Shift> shifts = new ArrayList<>();
        if(departments.isEmpty()) {
            return shifts;
        }
        Collections.sort(departments, Comparator.comparing(DepartmentSchedule::getStart));
        DepartmentSchedule prev = departments.get(0);
        int shiftStart = prev.getStart(), endShift = prev.getEnd();
        for (int i = 1; i < departments.size(); i++) {
            DepartmentSchedule current = departments.get(i);
            if(canBeMerged(endShift, current)) {
                endShift = current.getEnd();
            } else {
                shifts.add(new Shift(empId, shiftStart, endShift));
                shiftStart = current.getStart();
                endShift = current.getEnd();
            }
        }
        shifts.add(new Shift(empId, shiftStart, endShift));
        return shifts;
    }

    public List<Shift> fetchEmployeeScheduleOverLapping(Integer empId, List<DepartmentSchedule> departments) {
        List<Shift> shifts = new ArrayList<>();
        if(departments.isEmpty()) {
            return shifts;
        }
        Collections.sort(departments, Comparator.comparing(DepartmentSchedule::getStart));
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < departments.size(); i++) {
            int[] current = new int[]{departments.get(i).getStart(), departments.get(i).getEnd()};
            while(!stack.isEmpty() && overLaps(current, stack.peek())) {
                current = merge(current, stack.pop());
            }
            stack.push(current);
        }
        while (!stack.isEmpty()) {
            int[] shift = stack.pop();
            shifts.add(0, new Shift(empId, shift[0], shift[1]));
        }
        return shifts;
    }

    private int[] merge(int[] current, int[] pop) {
        return new int[] {Math.min(current[0], pop[0]), Math.max(current[1], pop[1])};
    }

    private boolean overLaps(int[] current, int[] peek) {
        return Math.max(current[0], peek[0]) <= Math.min(current[1], peek[1]);
    }

    private boolean canBeMerged(int endShift, DepartmentSchedule current) {
        return endShift == current.getStart();
    }
}
