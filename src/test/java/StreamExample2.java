import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamExample2 {
    
    @Test
    void test1(){
        List<Cat> cats = Arrays.asList(
                new Cat(2, "sana"),
                new Cat(3, "momo"),
                new Cat(1, "mina"),
                new Cat(4, "jihyo")
        );

        cats.stream()
                .sorted(Comparator.comparing(Cat::getNumber))
                .forEach(System.out::println);

        System.out.println("====================================================");

        cats.stream()
                .sorted(Comparator.comparing(Cat::getNumber).reversed())
                .forEach(System.out::println);
    }

    @Test
    void test2(){
        List<String> list = List.of("a", "bb", "acc", "ddd");
        list.stream()
                .map(s -> s.toUpperCase(Locale.ROOT))
                .forEach(System.out::println);

        System.out.println("====================================================");

        list.stream()
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s){
                        return s.toUpperCase(Locale.ROOT);
                    }
                }).forEach(System.out::println);
    }

    @Test
    void test3(){
    }

    @Test
    void test4(){
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

    @Test
    void test9() {
    }

    @Test
    void test10(){
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Cat{
        private int number;
        private String name;
    }

}


