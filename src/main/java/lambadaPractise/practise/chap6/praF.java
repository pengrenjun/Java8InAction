package lambadaPractise.practise.chap6;

import lambadaPractise.DataBase.DataContent;
import lambadaPractise.DataBase.DishPra;
import lambdasinaction.chap5.Trader;
import lambdasinaction.chap5.Transaction;

import java.util.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;


public class praF {

    private  static List<DishPra> dishPraList=DataContent.menu;

    private  static List<Transaction> transactionList=DataContent.transactions;

    public static void main(String[] args) {


        System.out.println("====================收集器归约汇总===========================");

        //查看菜单列表菜品总数 counting
        Long meanCount=dishPraList.stream().collect(counting());
        System.out.println(meanCount);


        //查找交易量最大的交易 maxBy
        Optional<Transaction> maxValtranscation=transactionList.stream().collect(maxBy(comparingInt(Transaction::getValue)));
        System.out.println(maxValtranscation.get());

        //汇总交易量 summingInt

        //收集器汇总
        int sumVal=transactionList.stream().collect(summingInt(Transaction::getValue));
        //reduce归约
        int sumValA=transactionList.stream().map(Transaction::getValue).reduce(Integer::sum).get();
        //数值流静态方法
        int sumValB=transactionList.stream().mapToInt(Transaction::getValue).sum();
        System.out.println(sumVal);
        System.out.println(sumValA);
        System.out.println(sumValB);


        //菜品的平均热量值 averagingInt
        double avgCalories = dishPraList.stream().collect(averagingInt(DishPra::getCalories));
        System.out.println(avgCalories);


        //获取菜品热量相关的数据 count=9, sum=4200, min=120, average=466.666667, max=800
        IntSummaryStatistics intSummaryStatistics=dishPraList.stream().collect(summarizingInt(DishPra::getCalories));
        System.out.println(intSummaryStatistics);

        // 连接字符串
        String dishPraListName=dishPraList.stream().map(DishPra::getName).collect(joining(","));
        System.out.println(dishPraListName);

        System.out.println("===================分组===========================");

        //对菜品种类进行分类
        Map<DishPra.Type, List<DishPra>> dishPraMap=dishPraList.stream().collect(groupingBy(DishPra::getType));
        System.out.println(dishPraMap);

        //把热量不到400卡路里的菜划分为“低热量”（diet），热量400到700 卡路里的菜划为“普通”（normal），高于700卡路里的划为“高热量”（fat）

        Map<DishPraLevel, List<DishPra>> dishesByCaloricLevel = dishPraList.stream().collect(groupingBy(praF::dishPraLevelfunction));

        System.out.println(dishesByCaloricLevel);


        //对交易数据按交易员进行分类
        Map<Trader,List<Transaction>> traderListMap=transactionList.stream().collect(groupingBy(Transaction::getTrader));
        System.out.println(traderListMap);

        System.out.println("===========================多级分组===========================");
        //多级分组
        //对菜品种类进行分类 然后再把每组按热量进行分组
        Map<DishPra.Type,Map<DishPraLevel, List<DishPra>>> mapMap=dishPraList.stream().
                collect(groupingBy(DishPra::getType,groupingBy(praF::dishPraLevelfunction)));
        System.out.println(mapMap);

        System.out.println("=====================对子组数据的收集==========================");

        //对子组数据的收集  groupingBy(分组函数,收集器);

        //不同种类的菜品各自的总数是多少
        Map<DishPra.Type, Long> typeIntegerMap=dishPraList.stream().collect(groupingBy(DishPra::getType,counting()));
        System.out.println(typeIntegerMap);

        //交易员各自的交易量总和是多少 groupingBy summingInt
        Map<Trader,Integer> traderLongMap=transactionList.stream().collect(groupingBy(Transaction::getTrader,summingInt(Transaction::getValue)));
        System.out.println(traderLongMap);

        //不同年份的最大交易量
        Map<Integer,Optional<Transaction>> integerOptionalMap=transactionList.stream().
                collect(groupingBy(Transaction::getYear,maxBy(comparingInt(Transaction::getValue))));
        System.out.println(integerOptionalMap);

        //把收集器的结果转换为另一种类型 collectingAndThen
        Map<Integer, Integer> integerIntegerMap =
                                transactionList.stream()
                                        .collect(groupingBy(Transaction::getYear,
                                                collectingAndThen(
                                                        maxBy(comparingInt(Transaction::getValue)),
                                                        option->option.get().getValue())));
        System.out.println(integerIntegerMap);

        //groupingBy与其他收集器联合使用(mapping)
        //mapping这个方法接受两个参数：一个函数对流中的元素做变换，另一个则将变换的结果对象收集起来

        //按交易员进行分组,并且得到各自交易员交易所在的年份 groupingBy mapping
        Map<Trader,Set<Integer>> traderSetMap=transactionList.stream().
                collect(groupingBy(Transaction::getTrader,
                        mapping(Transaction::getYear,toSet())));

        //确保set返回的类型 toCollection
        Map<Trader,Set<Integer>> traderSetMapB=transactionList.stream().
                collect(groupingBy(Transaction::getTrader,
                        mapping(Transaction::getYear,toCollection(HashSet::new))));

        System.out.println(traderSetMap);
        System.out.println(traderSetMapB);




    }


    public  enum DishPraLevel{diet,normal,fat}

    public static  DishPraLevel dishPraLevelfunction(DishPra dishPra) {
        if (dishPra.getCalories() <= 400) return DishPraLevel.diet;
        else if (dishPra.getCalories() <= 700) return
                DishPraLevel.normal;
        else return DishPraLevel.fat;
    }


}
