package ThreadCoreKnowledge.ThreadObjectMethod;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepDontReleaseLock  implements Runnable{
    private static final Lock lock =new ReentrantLock();
    @Override
    public void run() {
      lock.lock();
        System.out.println("线程"+Thread.currentThread().getName()+"获取到锁");
        try {
            Thread.sleep(5000);
            System.out.println("线程"+Thread.currentThread().getName()+"已经苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SleepDontReleaseLock releaseLock = new SleepDontReleaseLock();
        new Thread(()->releaseLock.run(),"thread").start();
        new Thread(()->releaseLock.run(),"thread2").start();

    }
}
