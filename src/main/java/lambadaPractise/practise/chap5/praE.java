package lambadaPractise.practise.chap5;

import lambadaPractise.DataBase.DataContent;
import lambadaPractise.DataBase.DishPra;
import lambdasinaction.chap5.Trader;
import lambdasinaction.chap5.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class praE {

    private  static List<DishPra> dishPraList=DataContent.menu;

    private  static List<Transaction> transactionList=DataContent.transactions;

    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");

        List<Integer> list=words.stream()
                            .map(String::length).collect(toList());
        System.out.println(list);


        //列出这些单词出现的所有字母
        String[] strArr=new String[]{"qwe","qwerty","asdqwe"};

        List<String> list1= Arrays.stream(strArr)
                .map(str->str.split(""))
                .flatMap(Arrays::stream)
                .distinct().collect(toList());
        System.out.println(list1);

        //生成数值对
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs =
                numbers1.stream()
                        .flatMap(i -> numbers2.stream()
                                .map(j -> new int[]{i, j})
                        )
                        .collect(toList());

        pairs.stream().forEach(System.out::println);

        //列表数据匹配 anyMatch,allMatch
        if(dishPraList.stream().anyMatch(DishPra::isVegetarian)){
            System.out.println("含有蔬菜类食物");
        }

        if(!dishPraList.stream().allMatch(DishPra::isVegetarian)){
            System.out.println("含有肉类食物");
        }

        //查找元素任意元素
        dishPraList.stream()
                .filter(DishPra::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        //查找第一个平方后能被3整除的元素
        List<Integer> list2=Arrays.asList(1,2,3,4,5,6,7,8,9);
        list2.stream()
                .map(x->Math.sqrt(x))
                .filter(x->x%3==0)
                .findFirst()
                .ifPresent(x-> System.out.println(x));

        //利用reduce进行列表数据的归约操作
        //求和 含有初始值
        Integer sum=list2.stream().reduce(0,(a,b)->a+b);
        System.out.println(sum);

        //不含有初始值 返回Optional
        Optional<Integer> optionalInteger=list2.stream().reduce(Integer::sum);
        System.out.println(optionalInteger.get());

        //求最大值和最小值
        Optional<Integer> integerOptionall=list2.stream().reduce((x,y)->x>y?x:y);
        System.out.println(integerOptionall.get());

        Optional<Integer> integerOptional2=list2.stream().reduce(Integer::min);
        System.out.println(integerOptional2.get());

        //map-reduce模式
        Integer count= dishPraList.stream()
                .map(DishPra::getCalories)
                .distinct()
                .map(d->1)
                .reduce(0,Integer::sum);

        System.out.println(count);

        System.out.println(dishPraList.stream().count());

        System.out.println("========================练习=========================================");
        //练习
        /**
         * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
         * (2) 交易员都在哪些不同的城市工作过？
         * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
         * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
         * (5) 有没有交易员是在米兰工作的？
         * (6) 打印生活在剑桥的交易员的所有交易额总和。
         * (7) 所有交易中，最高的交易额是多少？
         * (8) 找到交易额最小的交易。
         */
        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> transactionList1=transactionList.stream()
                .filter(transaction -> transaction.getYear()==2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(toList());
        System.out.println(transactionList1);

        //(2) 交易员都在哪些不同的城市工作过？
        List<String> cityList=transactionList.stream()
                .map(transaction->transaction.getTrader().getCity())
                .distinct().collect(toList());
        System.out.println(cityList);

        //(3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> traderList=transactionList.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getTrader())
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println(traderList);

        //(4) 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> stringList=transactionList.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted(comparing(String::toString)).collect(toList());

        String traderStr =
                transactionList.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(joining());
        System.out.println(stringList);
        System.out.println(traderStr);

        //(5) 有没有交易员是在米兰工作的？
       Boolean flag=transactionList.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
       if(flag) System.out.println("ok");

       //(6) 打印生活在剑桥的交易员的所有交易额总和。
        transactionList.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);

        //(7) 所有交易中，最高的交易额是多少？
        transactionList.stream().map(Transaction::getValue).max(Integer::compareTo).ifPresent(System.out::println);

        //(8) 找到交易额最小的交易。
        Transaction transaction=transactionList.stream().sorted(Comparator.comparingInt(Transaction::getValue)).findFirst().get();
        System.out.println(transaction);

        Optional<Transaction> smallestTransaction =
                transactionList.stream()
                        .reduce((t1, t2) ->
                                t1.getValue() < t2.getValue() ? t1 : t2);

        Optional<Transaction> smallestTransactionq =
                transactionList.stream()
                        .min(comparing(Transaction::getValue));






    }
}
