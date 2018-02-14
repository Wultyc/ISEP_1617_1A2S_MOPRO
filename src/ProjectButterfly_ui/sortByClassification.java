package ProjectButterfly_ui;

import java.util.Comparator;
import ProjectButterfly_core.Project;

public class sortByClassification implements Comparator<Project> {

    public int compare(Project proj1, Project proj2) {
        float classif1 = proj1.getClassification();
        float classif2 = proj2.getClassification();

        if (classif1 < classif2) {
            return -1;
        } else if (classif1 > classif2) {
            return 1;
        } else {
            return 0;
        }
    }
}
