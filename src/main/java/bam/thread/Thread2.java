package bam.thread;

import javax.sound.midi.Soundbank;

/**
 * 如何优雅的结束线程
 * 1.设置标志位，在线程中检测标志位，注意：标志位需要volatile修饰
 * 2.1当线程处于非阻塞状态时,设置线程的interrupted,此方法并不是结束线程,只是设置了线程的结束标志位,和方法1同理
 * 2.2在线程处于阻塞状态时,调用interrupt方法会报错,需要catch错误,并退出,此时interrupted状态会被重置成false
 */
public class Thread2 {

    private static volatile boolean endFlag = false;

    public static void test1() {
        while (!endFlag) {
            System.out.println("test1 running");
        }
        System.out.println("test1 end");
    }

    public static void test2() {
        endFlag = true;
    }


    public static void test3() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("test3 running;interrupted:" + Thread.currentThread().isInterrupted());
        }
        System.out.println("test3 end;interrupted:" + Thread.currentThread().isInterrupted());
    }

    public static void test4() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                System.out.println("test4 running;interrupted:" + Thread.currentThread().isInterrupted());
            }
            System.out.println("test4 end;interrupted:" + Thread.currentThread().isInterrupted());
        } catch (Exception e) {
            System.out.println("test4 end;interrupted:" + Thread.currentThread().isInterrupted());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        new Thread(Thread2::test1).start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        new Thread(Thread2::test2).start();

//        Thread t1 = new Thread(Thread2::test3);
//        t1.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        t1.interrupt();

        Thread t1 = new Thread(Thread2::test4);
        t1.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
    }
}
