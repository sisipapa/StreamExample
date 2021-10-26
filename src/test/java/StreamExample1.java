import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamExample1 {
    
    @Test
    void test1(){
        List<String> list = List.of("test1", "test2", "test3");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);
    }

    @Test
    void test2(){
        String[] arr = new String[]{"test1", "test2", "test3"};
        Stream<String> stream = Arrays.stream(arr, 0, 2);
        stream.forEach(System.out::println);
    }

    @Test
    void test3(){
        List<String> list = List.of("test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9", "test10", "test11");
        Stream<String> stream = list.parallelStream();
        stream.forEach(str -> {
            System.out.println(str + " : " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) { }
        });
    }

    @Test
    void test4(){
        IntStream intStream = IntStream.range(1, 5);
        intStream.forEach(System.out::println);

        IntStream closedIntStream = IntStream.rangeClosed(1, 5);
        closedIntStream.forEach(System.out::println);

        LongStream longStream = LongStream.range(1, 5);
        longStream.forEach(System.out::println);

        DoubleStream doubleStream = DoubleStream.of(0.1, 0.2, 0.3, 0.4);
        doubleStream.forEach(System.out::println);
    }

    @Test
    void test5(){
        IntStream intStream = new Random().ints().limit(5);
        intStream.forEach(System.out::println);

        DoubleStream doubleStream = new Random().doubles(5);
        doubleStream.forEach(System.out::println);
    }

    @Test
    void test6() throws Exception{
        Path path = Paths.get("C:\\IdeaProjects\\StreamExample\\src\\file");
        Stream<Path> list = Files.list(path);
        list.forEach(p -> System.out.println(p.getFileName()));

        System.out.println("============================================================");

        Path filePath = Paths.get("C:\\IdeaProjects\\StreamExample\\src\\file\\file1.txt");
        Stream<String> lines = Files.lines(filePath);
        lines.forEach(System.out::println);
    }

    @Test
    void test7() throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("C:\\IdeaProjects\\StreamExample\\src\\file\\file2.txt"));
        Stream<String> lines = br.lines();
        lines.forEach(System.out::println);
    }
}
