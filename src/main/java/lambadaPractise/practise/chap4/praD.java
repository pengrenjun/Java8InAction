package lambadaPractise.practise.chap4;

import lambadaPractise.DataBase.DataContent;
import lambadaPractise.DataBase.DishPra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class praD {

    private  static List<DishPra> dishPraList=DataContent.menu;

    public static void main(String[] args) {


        //获取肉类食物的热量前两名的食物名称列表

        List<String> filterList=
                dishPraList.stream()
                            .filter(dish->!dish.isVegetarian())
                            .sorted(Comparator.comparing(DishPra::getCalories).reversed())
                            .map(DishPra::getName).limit(2).collect(Collectors.toList());

        System.out.println(filterList);






    }
}
