package ThreadCoreKnowledge.JavaMemoryModel;

import java.util.concurrent.atomic.AtomicInteger;

//不适用volatile场景
public class NoVolatile  implements  Runnable{

    volatile int a;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable =new NoVolatile();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((NoVolatile) runnable).a);
        System.out.println(((NoVolatile) runnable).realA.get());
    }
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
             a++;
             realA.incrementAndGet();
        }
    }
}
