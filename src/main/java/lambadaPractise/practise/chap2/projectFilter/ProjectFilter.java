package lambadaPractise.practise.chap2.projectFilter;

import lambadaPractise.DataBase.Project;

/**
 * 函数式接口只能定义一个抽象方法
 */
@FunctionalInterface
public interface ProjectFilter {

    boolean test(Project project, Object arg);

}
