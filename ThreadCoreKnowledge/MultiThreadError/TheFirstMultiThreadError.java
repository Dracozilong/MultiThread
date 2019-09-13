package ThreadCoreKnowledge.MultiThreadError;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

//演示计数不准确(减少),找出错误点
public class TheFirstMultiThreadError implements Runnable {
    int index = 0;
    static TheFirstMultiThreadError instance= new TheFirstMultiThreadError();
    final boolean[] marked = new boolean[10000000];
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongIndex =new AtomicInteger();
    static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    static volatile CyclicBarrier CyclicBarrier2 = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> instance.run(), "thread1");
        Thread thread2 = new Thread(() -> instance.run(), "thread2");
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(instance.index);
        System.out.println("真正运行的次数"+realIndex);
        System.out.println("错误运行错误"+wrongIndex);
    }

    @Override public void run() {
        marked[0]= true;
        for (int i=0;i<10000;i++){
            try {
                CyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {

                cyclicBarrier1.reset();
                CyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (instance){
                if (marked[index] && marked[index-1]){
                    System.out.println("发生错误"+index);
                    wrongIndex.incrementAndGet();
                }
                marked[index]=true;
            }

        }
    }
}
