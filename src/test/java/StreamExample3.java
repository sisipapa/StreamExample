import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class StreamExample3 {
    
    @Test
    void test1(){
        // forEach 메서드는 병렬 스트림을 사용했을 때 순서를 보장할 수 없습니다. 따라서 스트림을 순서대로 순회하고 싶은 경우 forEachOrdered 메서드를 사용해야 합니다.
        List<Integer> list = List.of(1, 2, 3, 4, 5);

        // 매 실행마다 출력 결과가 동일하지 않다.
        list.parallelStream().forEach(System.out::println);

        System.out.println("====================================================");

        // 매 실행마다 동일한 출력 결과
        list.parallelStream().forEachOrdered(System.out::println);
    }

    @Test
    void test2(){
        // BinaryOperator를 사용하는데 이는 두 개의 같은 타입 요소를 인자로 받아 동일한 타입의 결과를 반환하는 함수형 인터페이스를 사용합니다.
        List<Integer> list = List.of(1, 2, 3);
        Optional<Integer> result = list.stream().reduce((a, b) -> a + b);
        System.out.println("BinaryOperator lambda : " + result.get());
        System.out.println("=================================================");
        System.out.println("BinaryOperator : " + list.stream().reduce(Integer::sum).get());
        System.out.println("=================================================");
        System.out.println("BinaryOperator 초기값 : " + list.stream().reduce(4, Integer::sum));
    }

    @Test
    void test3(){
        // 값을 누적하는 연산의 경우 병렬 연산의 결과를 결합해야 하는데, 여기서 세 번째 인자가 그 역할을 합니다.
        // 그러니까 병렬 처리를 하는 경우에 각자 다른 스레드의 결과를 합쳐줍니다.
        // 일반 스트림에서는 in combiner 내부로직이 수행안되고 병렬스트림에서는 in combiner가 수행된다.
        List<Integer> list = List.of(1, 2, 5);
        Integer result = list.stream()
                .reduce(1, Integer::sum, (a, b) -> {
                    System.out.println("in combiner - a:" + a + " b:" + b);
                    return a + b;
                });
        System.out.println(result);

        System.out.println("=================================================");

        Integer result2 = list.parallelStream()
                .reduce(1, Integer::sum, (a, b) -> {
                    System.out.println("in combiner - a:" + a + " b:" + b);
                    return a + b;
                });
        System.out.println(result2);
    }

    @Test
    void test4(){
        // min, max, count, sum, average

        // min
        OptionalDouble min = DoubleStream.of(4.12, 5.13, -1.32, 123.9, -15.7).min();
        min.ifPresent(System.out::println);

        System.out.println("=================================================");

        // max
        int max = IntStream.of(100, 400, 500, 300).max().getAsInt();
        System.out.println(max);
        System.out.println("=================================================");

        // count
        long count = IntStream.of(2, 4, 1, 3, 5, 7, 6).count();
        System.out.println(count);

        System.out.println("=================================================");

        // sum
        double sum = DoubleStream.of(3.0, 2.0, 1.0, 4.0).sum();
        System.out.println(sum);

        System.out.println("=================================================");

        // average
        OptionalDouble average = IntStream.of(3, 2, 1, 4, 5).average();
        average.ifPresent(System.out::println);
    }

    @Test
    void test5(){

    }

    @Test
    void test6(){
    }

    @Test
    void test7(){
    }

    @Test
    void test8() {
    }
}


