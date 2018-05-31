package lambadaPractise.practise.chap3;

/**
 * 项目有参构造函数引用接口
 * @param <A>
 * @param <B>
 * @param <C>
 * @param <D>
 * @param <E>
 * @param <F>
 * @param <G>
 */
@FunctionalInterface
public interface ProjectConstructorFunction<A,B,C,D,E,F,G> {
    G apply(A a,B b,C c,D d,E e,F f);

}
