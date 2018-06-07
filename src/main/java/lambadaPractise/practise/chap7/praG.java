package lambadaPractise.practise.chap7;

import java.util.function.Function;
import java.util.stream.Stream;

public class praG {

    public static void main(String[] args) {


        measuretest(praG::parallelSum,1_000_000L,"parallelSum");

    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /**
     * 执行测试
     * @param f     执行方法
     * @param input 输入数据
     * @param <T>
     * @param <R>
     * @return      执行时间
     */
    public static <T, R> long measuretest(Function<T, R> f, T input,String functionName) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if (duration < fastest) fastest = duration;
        }
        System.out.println(functionName+" 执行时间 "+fastest+" msecs");
        return fastest;
    }
}
