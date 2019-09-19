package ThreadCoreKnowledge.DeadLock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//用trylock避免死锁
public class TryLockDeadLock  implements Runnable{

    int flag =1;
    static Lock lock1  = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadLock lock1 = new TryLockDeadLock();
        TryLockDeadLock lock2 = new TryLockDeadLock();
        lock1.flag=1;
        lock2.flag=0;
        new Thread(()->lock1.run()).start();
        new Thread(()->lock2.run()).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag ==1){
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                        System.out.println("线程1获取到锁1");
                        Thread.sleep( new Random().nextInt(1000));
                        if (lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                            System.out.println("线程1获取到锁2");
                            System.out.println("线程1成功获取到了2把锁");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        }else {
                            System.out.println("线程1尝试获取锁2失败");
                            lock1.unlock();
                            Thread.sleep( new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println("线程1获取锁失败，已重试");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
            if (flag == 0){
                try {
                    if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)){
                        System.out.println("线程2获取到锁2");
                        Thread.sleep( new Random().nextInt(1000));
                        if (lock1.tryLock(3000,TimeUnit.MILLISECONDS)){
                            System.out.println("线程2成功获取到锁1");
                            System.out.println("线程2成功获取到了2把锁");
                            lock1.unlock();
                            lock2.unlock();
                            break;
                        }else {
                            System.out.println("线程2尝试获取锁1失败");
                            lock2.unlock();
                            Thread.sleep( new Random().nextInt(1000));
                        }
                    }else {
                        System.out.println("线程2获取锁2失败，已重试");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
