package lambadaPractise.practise.chap1;

import lambadaPractise.DataBase.DataContent;
import lambadaPractise.DataBase.Project;
import lambadaPractise.Util.DateUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * 过滤列表数据
 */
public class praA {

    public static void main(String[] args)  {

          List<Project> projectList=DataContent.projectList;
          //匿名的lambada表达方法进行数据过滤
          List<Project> projectList1=filterProjectList(projectList,(Project project)->"CUSTOMER1".equals(project.getCustomerNumber()));
          System.out.println(projectList1);

          System.out.println("===============================================================================");
          //采用定义方法的方式进行数据过滤
          List<Project> projectList2=filterProjectList(projectList, praA::filterProjectStartDate);
          System.out.println(projectList2);

    }

    /**
     * 定义过滤表达式方法
     * @param projects  项目列表数据
     * @param predicate 过滤条件
     * @return
     */
    public static List<Project> filterProjectList(List<Project> projects, Predicate<Project> predicate){
        List<Project> projectList=new ArrayList<>();
        for(Project project:projects){
            if(predicate.test(project)){
                projectList.add(project);
            }
        }
        return  projectList;
    }


    public static boolean filterProjectStartDate(Project project)  {

        try {
            return project.getStartDate().after(DateUtil.convertStrToDate("2018-01-01"));
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }


}
