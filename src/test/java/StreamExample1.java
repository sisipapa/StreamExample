import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample1 {
    
    @Test
    void test1(){
        // 컬렉션 구현 클래스의 stream 생성
        List<String> list = List.of("test1", "test2", "test3");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);
    }

    @Test
    void test2(){
        // Arrays.stream 메소드를 이용해 배열스트림 생성
        String[] arr = new String[]{"test1", "test2", "test3"};
        Stream<String> stream = Arrays.stream(arr, 0, 2);
        stream.forEach(System.out::println);
    }

    @Test
    void test3(){
        // 병렬스트림 생성
        List<String> list = List.of("test1", "test2", "test3", "test4", "test5", "test6");
        Stream<String> stream = list.parallelStream();
        stream.forEach(System.out::println);
    }
}
