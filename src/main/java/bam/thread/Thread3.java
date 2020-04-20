package bam.thread;

import java.util.ArrayList;
import java.util.List;

public class Thread3 {
    public static volatile List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                    System.out.println(i);
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("容器list已经到达一定数量;i:" + i);
                        return;
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (list.size() == 5) {
                        t1.interrupt();
                        return;
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
