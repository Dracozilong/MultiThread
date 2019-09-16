package ThreadCoreKnowledge.JavaMemoryModel;

import java.util.concurrent.atomic.AtomicInteger;

//正确使用volatile的情况1
public class VolatileUseCorrcet implements Runnable {
    volatile boolean flag=false;
    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable =new VolatileUseCorrcet();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(((VolatileUseCorrcet) runnable).flag);
        System.out.println(((VolatileUseCorrcet) runnable).realA.get());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setflag();
            realA.incrementAndGet();
        }
    }

    private void setflag() {
        flag=true;
    }
}
