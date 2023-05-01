package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeWork {
    private int employeeId;

    private final List<Project> projects = new ArrayList<>();

    public EmployeeWork(int employeeId) {
        setEmployeeId(employeeId);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }
}
