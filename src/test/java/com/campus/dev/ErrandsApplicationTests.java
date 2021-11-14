package com.campus.dev;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class ErrandsApplicationTests {

    @Test
    void contextLoads() {
    }


    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4);


        list.stream().filter(it->it%2==1).collect(Collectors.toList());
        list.stream().filter(it->it%2==0).collect(Collectors.toList());

    }
}
