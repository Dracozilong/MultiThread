package Lock.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    static Lock lock = new ReentrantLock(false); //true 对应公平锁，false对应非公平锁

    public static void  main(String[] args) throws InterruptedException{
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadDemo(i)).start();
        }
    }

    static  class  ThreadDemo implements Runnable{
        Integer id;

        public ThreadDemo(Integer id) {
            this.id = id;
        }

        @Override
        public void run() {

            for (int i = 0; i < 2; i++) {
                lock.lock();
                System.out.println("获得锁的线程"+id);
                lock.unlock();
            }
        }
    }
}
