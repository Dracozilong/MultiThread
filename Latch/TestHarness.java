package Latch;

import java.util.concurrent.CountDownLatch;

public class TestHarness {

    public long timeTask(int nThread, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThread);
        for (int i = 0; i < nThread; i++) {
            Thread thread = new Thread() {
                public void run() {
                        try {
                            System.out.println(Thread.currentThread().getName()+" : pre run.....");
                            startGate.await();
                            System.out.println(Thread.currentThread().getName()+" : runing....");
                        try {
                    task.run();

                } finally {
                    endGate.countDown();
                }
            } catch (InterruptedException ignored) {
            }
                }
            };
            thread.start();
        }
        long start = System.nanoTime();
        System.out.println("before startGate countDown");
        startGate.countDown(); //没有计数减少，已经开启的线程就不会运行，类似于马栏 ，所有的马都准备就绪，但是不放马栏，马就不会跑
        System.out.println("after startGate countDown");
        endGate.await(); //如果没有endGate.await() 就是当最快的线程完成后 就会计算结束时间 ，而不是当所有的线程都完成以后才计算时间
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
         TestHarness testHarness=new TestHarness();
        long timeTask = 0;
        try {
            timeTask = testHarness.timeTask(5, new Runnable() {
                @Override
                public void run() {

                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(timeTask);
    }
}

