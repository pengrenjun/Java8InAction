package lambadaPractise.practise.chap2.projectFilter;

import lambadaPractise.DataBase.Project;

public interface ProjectFilter {

    boolean test(Project project, Object arg);
}
