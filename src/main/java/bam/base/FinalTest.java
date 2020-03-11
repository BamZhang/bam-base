package bam.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Final关键字可以修饰类，方法以及变量
 * 1、final在修饰类时，类不允许被继承
 * 2、final在修饰方法时，方法不允许被重写，父类中的私有方法除外
 * 3、在修饰变量时，是指变量的内存地址不允许修改,对象本身属性可以被修改
 */
public class FinalTest {

    public static final String str = "固定字符串";
    public static final Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        map.put("test", "test");
//        str = "";
        System.out.println(map.toString());
    }
}
