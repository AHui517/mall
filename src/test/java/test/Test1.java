package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: mall
 * @description: null
 * @packagename: test
 * @author: Kong
 * @date: 2021-07-13 11:49
 **/
public class Test1 {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(null);
        }
        System.out.println(list.size());
    }
}
