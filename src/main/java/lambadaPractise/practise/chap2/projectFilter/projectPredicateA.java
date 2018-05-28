package lambadaPractise.practise.chap2.projectFilter;

import lambadaPractise.DataBase.Project;

import java.util.Date;

/**
 * 按项目的开始时间进行筛选(项目开始时间>给定的时间)
 */
public class projectPredicateA implements  ProjectFilter{

    @Override
    public boolean test(Project project, Object arg) {
        return  project.getStartDate().after((Date) arg);
    }
}
