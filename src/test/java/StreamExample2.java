import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    void test4(){
        List<String> list1 = List.of("a1", "a2");
        List<String> list2 = List.of("b1", "b2");
        List<List<String>> newList = List.of(list1, list2);

        newList.stream()
                .flatMap(new Function<List<String>, Stream<String>>() {
                    @Override
                    public Stream<String> apply(List<String> strings) {
                        return strings.stream();
                    }
                }).collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("====================================================");

        newList.stream()
                .flatMap(list -> list.stream())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    void test5(){

        List<String> strList = List.of("t3", "t1", "t2", "t1", "t4", "t3");
        strList.stream()
                .distinct()
                .forEach(System.out::println);

        System.out.println("====================================================");

        Foo foo1 = new Foo("111");
        Foo foo2 = new Foo("222");
        Foo foo3 = new Foo("111");
        Foo foo4 = new Foo("444");
        Foo foo5 = new Foo("555");
        List<Foo> objList = List.of(foo1, foo2, foo3, foo4, foo5, foo1, foo2, foo3);

        objList.stream().distinct()
                .forEach(System.out::println);
    }

    @Test
    void test6(){
        // peek ???????????? ????????? ?????? ????????? ????????? ???????????? ?????? ????????? ???????????? ?????????.
        // ?????? ??????????????? ????????? ???????????? ?????? ????????? ?????? ?????? ????????? ????????? ????????? ??? ???????????????.
        // ????????? ?????? peek ????????? ?????? ????????? ???????????? ????????? ???????????? ?????? ????????????.
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> filterList = list.stream()
                .filter(i -> i > 1 && i < 5)
                .peek(System.out::println)
                .filter(i -> i > 3)
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println("###RESULT###");
        filterList.stream().forEach(System.out::println);
    }

    @Test
    void test7(){
        // limit ???????????? ???????????? ????????? ?????? ?????? ????????? ????????? ??? ????????????.
        List<String> list = List.of("a", "b", "c", "d", "e").stream()
                .limit(3)
                .collect(Collectors.toList());

        System.out.println(list);
    }

    @Test
    void test8() {
        // skip ???????????? ???????????? ????????? ?????? ??? ?????? ???????????? ????????? ?????????
        // ?????? ????????? ????????? ????????? ????????? ????????? ????????? ????????? ???????????? ???????????????.
        List<String> list = Arrays.stream(new String[]{"a", "b", "c", "d", "e"})
                .skip(3)
                .collect(Collectors.toList());

        System.out.println(list);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Cat{
        private int number;
        private String name;
    }

    static class Foo {
        private String bar;
        public Foo(String bar) {
            this.bar = bar;
        }

        public String toString() {
            return "bar: " + bar;
        }
    }
}


