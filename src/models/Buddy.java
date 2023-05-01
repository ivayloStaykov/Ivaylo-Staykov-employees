package models;

public class Buddy {
    private int employeeId;
    private long workTimeTogether = 0 ;

    public Buddy(int employeeId, long workTimeTogether) {
        setEmployeeId(employeeId);
        setWorkTimeTogether(workTimeTogether);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public long getWorkTimeTogether() {
        return workTimeTogether;
    }

    public void setWorkTimeTogether(long workTimeTogether) {
        this.workTimeTogether += workTimeTogether;
    }
}
