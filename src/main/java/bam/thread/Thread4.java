package bam.thread;

import java.util.ArrayList;
import java.util.List;

public class Thread4 {

    private static volatile List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("thread1 " + i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (list.size() == 5) {
                        System.out.println("xxxxxxxx");
                        break;
                    }
                }
                System.out.println("thread2 end");
            }
        }).start();
    }


}
