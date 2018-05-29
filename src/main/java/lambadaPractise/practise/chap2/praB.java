package lambadaPractise.practise.chap2;

import com.opslab.util.ObjectUtils;
import lambadaPractise.DataBase.DataContent;
import lambadaPractise.DataBase.Project;
import lambadaPractise.Util.DateUtil;
import lambadaPractise.practise.chap2.projectFilter.ProjectFilter;
import lambadaPractise.practise.chap2.projectFilter.projectPredicateA;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 行为参数化,java匿名类的使用
 * @Description 对项目列表数据实现灵活的条件筛选
 */
public class praB {

   private static final List<Project> projectList=DataContent.projectList;


    public static void main(String[] args) throws ParseException {

        List<Project> projectListA=filterProject(projectList,new projectPredicateA(),"2018-01-01");
        System.out.println(projectListA);

        System.out.println("=============================================");


        //使用匿名内部类进行数据筛选(将接口实现类的方法放在的内部类中)
        List<Project> projectListB=filterProject(projectList, new ProjectFilter() {
            @Override
            public boolean test(Project project, Object arg) {
                return  project.getStartDate().after((Date) arg);
            }
        },"2018-01-01");

        System.out.println(projectListB);


        System.out.println("=====================================================");
        //列表数据排序
         projectList.sort(new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.getStartDate().compareTo(o2.getStartDate());
            }
        });

        System.out.println(projectList);

    }

    /**
     * 行为参数化
     * @param projects
     * @param projectFilter
     * @param arg
     * @return
     * @throws ParseException
     */
    public static List<Project> filterProject(List<Project> projects, ProjectFilter projectFilter,Object arg) throws ParseException {
        List<Project> projectList=new ArrayList<>();
        for(Project project:projects){
            if(projectFilter.test(project,DateUtil.convertStrToDate(String.valueOf(arg)))){
                projectList.add(project);
            }
        }
        return projectList;
    }
}
