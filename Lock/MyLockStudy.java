package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLockStudy implements  Runnable {

    private int count;

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        lock.lock();
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + ": ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        MyLockStudy myLockStudy = new MyLockStudy();
        Thread thread1 = new Thread(myLockStudy,"thread1");
        Thread thread2 = new Thread(myLockStudy,"thread2");
        Thread thread3 = new Thread(myLockStudy,"thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
