package models;

import java.util.Date;

public class Project {
    private int id;
    private Date start;
    private Date end;

    public Project(int id, Date start, Date end) {
        setId(id);
        setStart(start);
        setEnd(end);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
