package bam.thread;

/**
 * sleep和yield不释放对象锁
 * wait只能在同步代码块中使用,会释放锁,否则没有办法让其他线程调用notify方法,notify是不会释放锁的,会在自身的线程执行完以后,释放锁
 */
public class Thread1 {

    private static Object o = new Object();

    public static void test1() {
        synchronized (o) {
            System.out.println("test1 start");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test1 end");
        }
    }

    public static void test2() {
        synchronized (o) {
            System.out.println("test2 end");
        }
    }

    public static void test3() {
        synchronized (o) {
            try {
                System.out.println("test3 start");
                o.wait();
                System.out.println("test3 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void test4() {
        synchronized (o) {
            System.out.println("test4 start");
            o.notify();
            System.out.println("test4 end");
        }
    }

    public static void main(String[] args) {
//        new Thread(Thread1::test1, "").start();
//        new Thread(Thread1::test2, "").start();
        new Thread(Thread1::test3, "").start();
        new Thread(Thread1::test4, "").start();
    }
}
