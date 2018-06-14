package lambadaPractise.practise.chap7;

import lambdasinaction.chap7.ForkJoinSumCalculator;

import java.util.concurrent.RecursiveTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;


public class praG {

    public static void main(String[] args) {

        int p=Runtime.getRuntime().availableProcessors();
        System.out.println("处理器数量"+p);

        /**全局设置线程池的大小*/
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","2");

        measuretest(praG::forSum,1_000_000L,"forSum");
        measuretest(praG::iteratesum,1_000_000L,"iteratesum");
        measuretest(praG::parallelSum,1_000_000L,"parallelSum");
        measuretest(praG::longStreamParalSum,1_000_000L,"longStreamParalSum");
        measuretest(ForkJoinSumCalculator::forkJoinSum,1_000_000L,"forkJoinSum");



    }

    /**
     * 正常的求和计算
     * 用传统for循环的迭代版本执行起来应该会快很多，因为它更为底层，更重要的是不需要对
     * 原始类型做任何装箱或拆箱操作。
     */
    public static long forSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }


    /**
     * 自然数无限流求和计算
     */
    public static  long iteratesum(long n){
        return Stream.iterate(1L,i->i+1)
                .limit(n)
                .reduce(0L,Long::sum);
    }


    /**
     * 将流转换为并行流
     *
     *  iterate生成的是装箱的对象，必须拆箱成数字才能求和；
     *  我们很难把iterate分成多个独立块来并行执行。iterate很难分割成能够独立执行的小块，因为每次应用这个函数都要依赖前一次应用的结果
     * 整张数字列表在归纳过程开始时没有准备好，因而无法有效地把流划分为小块来并行处理。把流标记成并行，你其实是给顺序处理增加了开销，它还要把每次求和操作分到一个不同的线程上
     * @param n
     * @return
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
}

    /**
     * 使用LongStream.rangeClosed方法生成数值流进行处理
     */
    public static long longStreamParalSum(long n){
        return LongStream.rangeClosed(0,n).parallel().sum();
    }



    /**
     * 执行效率测试
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
