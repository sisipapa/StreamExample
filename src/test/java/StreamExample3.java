import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

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
        List<Food> list = new ArrayList<>();
        list.add(new Food("aaa", 300));
        list.add(new Food("bbb", 400));
        list.add(new Food("ccc", 100));
        list.add(new Food("ddd", 100));

        // map으로 Food Object에서 name만을 추출 List<String> 객체로 반환
        List<String> nameList = list.stream()
                .map(Food::getName)
                .collect(Collectors.toList());
        nameList.forEach(System.out::println);
        System.out.println("=================================================");

        // name 길이의 합 구하기
        Integer summingName = list.stream()
                .collect(Collectors.summingInt(s -> s.getName().length()));
        System.out.println(summingName);
        System.out.println("=================================================");

        // mapToInt 메서드로 칼로리(cal) 합 구하기
        int sum = list.stream().mapToInt(Food::getCal).sum();
        System.out.println(sum);
        System.out.println("=================================================");

        // Collectors.averagingInt 평균 구하기
        Double averageInt = list.stream()
                .collect(Collectors.averagingInt(Food::getCal));
        System.out.println(averageInt);
        System.out.println("=================================================");

        // Collectors.averagingDouble 평균 구하기
        Double averageDouble = list.stream()
                .collect(Collectors.averagingDouble(Food::getCal));
        System.out.println(averageDouble);
        System.out.println("=================================================");

        // Collectors.summarizingInt 통계함수
        IntSummaryStatistics summaryStatistics = list.stream()
                .collect(Collectors.summarizingInt(Food::getCal));

        System.out.println("평균 : " + summaryStatistics.getAverage());
        System.out.println("개수 : " + summaryStatistics.getCount());
        System.out.println("최댓값 : " + summaryStatistics.getMax());
        System.out.println("최솟값 : " + summaryStatistics.getMin());
        System.out.println("합계 : " + summaryStatistics.getSum());
    }

    @Test
    void test6(){
        List<Food> list = new ArrayList<>();
        list.add(new Food("aaa", 300));
        list.add(new Food("bbb", 400));
        list.add(new Food("ccc", 100));
        list.add(new Food("ddd", 100));

        // without arguments
        String defaultJoining = list.stream()
                .map(Food::getName).collect(Collectors.joining());

        System.out.println(defaultJoining);

        // delimiter
        String delimiterJoining = list.stream()
                .map(Food::getName).collect(Collectors.joining(","));

        System.out.println(delimiterJoining);

        // delimiter, prefix, suffix
        String combineJoining = list.stream()
                .map(Food::getName).collect(Collectors.joining(",", "[", "]"));

        System.out.println(combineJoining);
    }

    @Test
    void test7(){
        List<Food> list = new ArrayList<>();
        list.add(new Food("aaa", 300));
        list.add(new Food("bbb", 400));
        list.add(new Food("ccc", 100));
        list.add(new Food("ddd", 100));

        // 칼로리(cal)로 그룹 만들기
        Map<Integer, List<Food>> calMap = list.stream()
                .collect(Collectors.groupingBy(Food::getCal));

        System.out.println(calMap);

        System.out.println("=================================================");

        // partitioningBy는 인자로 Predicate 함수형 인터페이스를 받습니다. Predicate는 인자를 받아서 참 또는 거짓을 반환하기 때문에 boolean 값으로 그룹핑합니다.
        Map<Boolean, List<Food>> partitionMap = list.stream()
                .collect(Collectors.partitioningBy(food -> food.getCal() > 100));

        System.out.println(partitionMap);
    }

    @Test
    void test8() {
        // 키에 값이 2개 이상 존재하게 되는 경우 컬렉터는 IllegalStateException을 던집니다. 따라서 키가 중복되는 예외 상황을 해결하기 위해 BinaryOperator 인자를 추가할 수 있습니다.
        List<Food> list = new ArrayList<>();
        list.add(new Food("aaa", 300));
        list.add(new Food("bbb", 400));
        list.add(new Food("ccc", 100));
        list.add(new Food("ddd", 100));

        // 동일한 키가 있는 경우 새 값으로 대체한다.
        Map<Integer, String> map = list.stream()
                .collect(Collectors.toMap(
                        o -> o.getCal(),
                        o -> o.getName(),
                        (oldValue, newValue) -> newValue));

        System.out.println(map);
    }

    @Test
    void test9() {
        List<Food> list = new ArrayList<>();
        list.add(new Food("aaa", 300));
        list.add(new Food("bbb", 400));
        list.add(new Food("ccc", 100));
        list.add(new Food("ddd", 100));

        Food food = list.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Food::getCal)),
                        (Optional<Food> o) -> o.orElse(null)));

        System.out.println(food);
    }

    @Test
    void test10() {
        List<Food> list = new ArrayList<>();
        list.add(new Food("aaa", 300));
        list.add(new Food("bbb", 400));
        list.add(new Food("ccc", 100));
        list.add(new Food("ddd", 100));

        boolean anyMatch = list.stream()
                .anyMatch(food -> food.getCal() > 300);
        assertTrue(anyMatch);

        boolean allMatch = list.stream()
                .allMatch(food -> food.getCal() > 100);
        assertFalse(allMatch);

        boolean noneMatch = list.stream()
                .noneMatch(food -> food.getCal() < 1000);
        assertFalse(noneMatch);
    }

    @Getter
    @NoArgsConstructor
    static class Food {
        public Food(String name, int cal) {
            this.name = name;
            this.cal = cal;
        }

        private String name;
        private int cal;

        @Override
        public String toString() {
            return String.format("name: %s, cal: %s", name, cal);
        }

        // getter, setter 생략
    }
}


