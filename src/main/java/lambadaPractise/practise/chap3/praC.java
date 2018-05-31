package lambadaPractise.practise.chap3;

import lambadaPractise.DataBase.DataContent;
import lambadaPractise.DataBase.Project;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

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

        List<String> stringList = Arrays.asList("a","b","A","B");
        stringList.sort(String::compareToIgnoreCase);

        System.out.println(stringList);


        //构造函数的引用
        Supplier<Project> projectSupplier=Project::new;

        Project project1=projectSupplier.get();

        Project project2=projectSupplier.get();

        ProjectConstructorFunction<String,Integer,Date,String,String,String,Project > function=Project::new;

        Project projectC=function.apply("qwe",1,new Date(),"sdfg","adsf","sfdgsdfg");
        System.out.println(projectC);


        //函数复合
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        System.out.println(h.apply(1));

        Function<Integer, Integer> fa = x -> x + 1;
        Function<Integer, Integer> ga = x -> x * 2;
        Function<Integer, Integer> ha = fa.compose(ga);
        int resulta = ha.apply(1);
        System.out.println(resulta);









    }

    public  static  String concatStr(String str1, String str2, BiFunction<String,String,String> function){
             return function.apply(str1,str2);
    }


}
