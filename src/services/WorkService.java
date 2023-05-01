package services;

import common.CONSTANTS;
import models.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WorkService {
    public static List<EmployeeWork> employeeProjects(List<Employee> employees) throws ParseException {
        List<EmployeeWork> employeeWorks = new ArrayList<>();
        for (Employee e : employees) {
            if (employeeWorks.size() == 0) {
                employeeWorks.add(new EmployeeWork(e.getId()));
                employeeWorks.get(0).addProject(
                        new Project(e.getProjectId(), CONSTANTS.SF.parse(e.getFrom()), CONSTANTS.SF.parse(e.getTo()))
                );
            } else {
                if (employeeWorks.stream().anyMatch(id -> id.getEmployeeId() == e.getId())) {
                    //get() should work everytime because I check match in the if
                    employeeWorks.stream()
                            .filter(c -> c.getEmployeeId() == e.getId())
                            .findAny().get().addProject(
                                    new Project(e.getProjectId(), CONSTANTS.SF.parse(e.getFrom()), CONSTANTS.SF.parse(e.getTo()))
                            );
                } else {
                    employeeWorks.add(new EmployeeWork(e.getId()));
                    employeeWorks.get(employeeWorks.size() - 1).addProject(
                            new Project(e.getProjectId(), CONSTANTS.SF.parse(e.getFrom()), CONSTANTS.SF.parse(e.getTo()))
                    );
                }
            }
        }
        return employeeWorks;
    }

    public static List<EmployeeWorkTime> employeeTotalWork(List<EmployeeWork> employeeWorks) {
        List<EmployeeWorkTime> workTimes = new ArrayList<>();
        for (EmployeeWork current : employeeWorks) {
            Date min = current.getProjects().get(0).getStart();
            Date max = current.getProjects().get(0).getEnd();
            for (int j = 1; j < current.getProjects().size(); j++) {
                if (current.getProjects().get(j).getStart().before(min)) {
                    min = current.getProjects().get(j).getStart();
                }
                if (current.getProjects().get(j).getEnd().after(max)) {
                    max = current.getProjects().get(j).getEnd();
                }
            }
            workTimes.add(new EmployeeWorkTime(current.getEmployeeId(), min, max));

        }
        return workTimes;
    }

    public static double percentWorked(List<EmployeeWorkTime> workTimes, int id, long total) {
        EmployeeWorkTime workTogether = workTimes.stream().filter(e -> e.getEmployeeId() == id).findAny().get();
        long diff = workTogether.getNewestProject().getTime() - workTogether.getOldestProject().getTime();
        long employeeTotalDaysWorked = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
       return  ((double) total / employeeTotalDaysWorked) * 100;
    }
}
