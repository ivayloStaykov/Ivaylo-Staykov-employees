package models;

import java.util.ArrayList;
import java.util.List;

public class Relations {
    private int employeeId;

    private final List<Buddy> buddies = new ArrayList<>();

    public Relations(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public List<Buddy> getBuddies() {
        return buddies;
    }

    public void addBuddy(Buddy buddy) {
        this.buddies.add(buddy);
    }
}
