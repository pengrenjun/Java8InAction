package lambadaPractise.DataBase;

import lambadaPractise.Util.DateUtil;
import lambdasinaction.chap5.Trader;
import lambdasinaction.chap5.Transaction;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 练习的数据内容
 */
public class DataContent {

    public static final List<Project> projectList=new ArrayList<>();

    public static final List<DishPra> menu = Arrays.asList(
            new DishPra("pork", false, 800, DishPra.Type.MEAT),
            new DishPra("beef", false, 700, DishPra.Type.MEAT),
            new DishPra("chicken", false, 400, DishPra.Type.MEAT),
            new DishPra("french fries", true, 530, DishPra.Type.OTHER),
            new DishPra("rice", true, 350, DishPra.Type.OTHER),
            new DishPra("season fruit", true, 120, DishPra.Type.OTHER),
            new DishPra("pizza", true, 550, DishPra.Type.OTHER),
            new DishPra("prawns", false, 300, DishPra.Type.FISH),
            new DishPra("salmon", false, 450, DishPra.Type.FISH) );


    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario","Milan");
    static Trader alan = new Trader("Alan","Cambridge");
    static Trader brian = new Trader("Brian","Cambridge");

    //交易信息
    public static final List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2012, 1500),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );


    static {
        try {
            projectList.add(new Project("项目A",1,DateUtil.convertStrToDate("2017-01-29"),"小明","pcode1","CUSTOMER1"));
            projectList.add(new Project("项目B",2,DateUtil.convertStrToDate("2018-05-28"),"小李","pcode2","CUSTOMER2"));
            projectList.add(new Project("项目C",1,DateUtil.convertStrToDate("2018-05-21"),"阿强","pcode246456734567","CUSTOME5785"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
