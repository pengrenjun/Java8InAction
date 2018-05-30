package lambadaPractise.practise.chap3;

import lambadaPractise.DataBase.DataContent;
import lambadaPractise.DataBase.Project;

import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * Lambada表达式的使用
 */
public class praC {

    private static final List<Project> projectList=DataContent.projectList;

    private static final String str1="qweadsfa";

    private static final String str2="qwe";


    public static void main(String[] args) {

        //合并两个字符串
        String str=concatStr(str1,str2,(String a,String b)->a+""+b);

        System.out.println(str);

         int portNumber = 1337;
         Runnable r = () -> System.out.println(portNumber);
       // portNumber=123;


        r.run();


        //引用方法进行排序
        projectList.sort(Comparator.comparing(Project::getStartDate));

        System.out.println(projectList);



    }

    public  static  String concatStr(String str1, String str2, BiFunction<String,String,String> function){
             return function.apply(str1,str2);
    }


}
