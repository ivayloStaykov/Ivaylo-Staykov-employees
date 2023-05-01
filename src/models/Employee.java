package models;

import validators.ValidateEmployee;


public class Employee {
    private int id;
    private int projectId;
    private String from;
    private String to;

    public Employee(int id, int projectId, String from, String to) {
        setId(id);
        setProjectId(projectId);
        setFrom(from);
        setTo(to);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = ValidateEmployee.validateDate(from);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = ValidateEmployee.validateDate(to);
    }
}
