package services;

import models.Buddy;
import models.EmployeeWork;
import models.Project;
import models.Relations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RelationService {

    public static List<Relations> getRelations(List<EmployeeWork> works) {
        List<Relations> relations = new ArrayList<>();

        for (int i = 0; i < works.size(); i++) {
            EmployeeWork check = works.get(i);
            int checkId = check.getEmployeeId();
            List<Project> checkProjects = check.getProjects();
            relations.add(new Relations(checkId));
            for (EmployeeWork toCheck : works) {
                int toCheckId = toCheck.getEmployeeId();
                List<Project> toCheckProjects = toCheck.getProjects();
                if (checkId != toCheckId) {
                    for (int k = 0; k < checkProjects.size(); k++) {
                        int current = checkProjects.get(k).getId();
                        if (toCheckProjects.stream().anyMatch(p -> p.getId() == current)) {
                            Project found = toCheckProjects.stream().filter(p -> p.getId() == current).findAny().get();
                            if (checkDate(found, check, k)) {
                                Date start = leastStart(found.getStart(), checkProjects.get(k).getStart());
                                Date end = leastEnd(found.getEnd(), checkProjects.get(k).getEnd());
                                long diff = end.getTime() - start.getTime();
                                long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                                List<Buddy> buddies = relations.get(i).getBuddies();
                                if (buddies.stream().anyMatch(buddy -> buddy.getEmployeeId() == toCheckId)) {
                                    buddies.stream().filter(buddy -> buddy.getEmployeeId() == toCheckId).findAny().get().setWorkTimeTogether(days);
                                } else {
                                    relations.get(i).addBuddy(new Buddy(toCheckId, days));
                                }

                            }
                        }
                    }
                }
            }
        }
        return relations;
    }

    public static Date leastEnd(Date a, Date b) {
        return a == null ? b : (b == null ? a : (a.before(b) ? a : b));
    }

    public static Date leastStart(Date a, Date b) {
        return a == null ? b : (b == null ? a : (a.after(b) ? a : b));
    }

    public static boolean checkDate(Project found, EmployeeWork check, int k) {
        return after(found, check, k) | equal(found, check, k)
                && before(found, check, k) || equalEnd(found, check, k);
    }

    public static boolean after(Project found, EmployeeWork currentI, int k) {
        return found.getStart().after(currentI.getProjects().get(k).getStart());
    }

    public static boolean equal(Project found, EmployeeWork currentI, int k) {
        return found.getStart().equals(currentI.getProjects().get(k).getStart());
    }

    public static boolean equalEnd(Project found, EmployeeWork currentI, int k) {
        return found.getStart().equals(currentI.getProjects().get(k).getEnd());
    }

    public static boolean before(Project found, EmployeeWork currentI, int k) {
        return found.getStart().before(currentI.getProjects().get(k).getEnd());
    }
}
