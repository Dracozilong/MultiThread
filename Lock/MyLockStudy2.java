package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockStudy2 implements Runnable {

    private int count;
    Lock l = new ReentrantLock();

    @Override
    public void run() {
        if (l.tryLock()) {
            System.out.println(Thread.currentThread().getName() + "获取锁");
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            l.unlock();
        } else {
            System.out.println(Thread.currentThread().getName() + "未获取锁");
        }

    }

    public static void main(String args[]) {
        MyLockStudy runn = new MyLockStudy();
        Thread thread1 = new Thread(runn, "thread1");
        Thread thread2 = new Thread(runn, "thread2");
        Thread thread3 = new Thread(runn, "thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

}

