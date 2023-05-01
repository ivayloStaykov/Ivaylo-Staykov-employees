package models;

import java.util.Date;

public class EmployeeWorkTime {
    private int employeeId;

    private Date oldestProject;

    private Date newestProject;

    public EmployeeWorkTime(int employeeId, Date oldestProject, Date newestProject) {
        this.employeeId = employeeId;
        this.oldestProject = oldestProject;
        this.newestProject = newestProject;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getOldestProject() {
        return oldestProject;
    }

    public void setOldestProject(Date oldestProject) {
        this.oldestProject = oldestProject;
    }

    public Date getNewestProject() {
        return newestProject;
    }

    public void setNewestProject(Date newestProject) {
        this.newestProject = newestProject;
    }
}
