import models.*;
import services.RelationService;
import services.WorkService;
import services.ReadCSV;

import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        List<Employee> employees = ReadCSV.readEmployeesFromCSV("src/data/TestData.csv");
        List<EmployeeWork> employeeWorks = WorkService.employeeProjects(employees);
        List<EmployeeWorkTime> workTimes = WorkService.employeeTotalWork(employeeWorks);
        List<Relations> relations = RelationService.getRelations(employeeWorks);


        long max = 0;
        int maxEmployeeId = 0;
        int maxBuddyId = 0;
        for (Relations relation : relations) {
            for (int j = 0; j < relation.getBuddies().size(); j++) {
                if (max < relation.getBuddies().get(j).getWorkTimeTogether()) {
                    max = relation.getBuddies().get(j).getWorkTimeTogether();
                    maxEmployeeId = relation.getEmployeeId();
                    maxBuddyId = relation.getBuddies().get(j).getEmployeeId();
                }
            }
        }
        System.out.println("The longest any employees have worked together is:");
        System.out.printf("Employee: #%d, has worked with Employee: #%d for %d Days\n", maxEmployeeId, maxBuddyId, max);
        double employeePercent = WorkService.percentWorked(workTimes, maxEmployeeId, max);
        double buddyPercent = WorkService.percentWorked(workTimes, maxBuddyId, max);
        System.out.printf("Which is %.2f%% of the total time Employee #%d has spend on projects.\n", employeePercent, maxEmployeeId);
        System.out.printf("And is %.2f%% of the total time Employee #%d has spend on projects.\n", buddyPercent, maxBuddyId);
    }

}