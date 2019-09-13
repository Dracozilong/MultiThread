package ThreadCoreKnowledge.ThreadObjectMethod;

// 三个线程，线程1和线程2首先被阻塞，线程3唤醒他们。notify，notifyAll()
//start先执行并不代表线程先启动
public class WaitNotifyAll implements Runnable{

    private static final Object resourceA=new Object();

    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName()+"got resourceA lock");
            try {
                System.out.println(Thread.currentThread().getName()+"waits to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName()+"waiting to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable =new WaitNotifyAll();
        Thread threadA = new Thread(runnable);
        Thread threadB = new Thread(runnable);
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    resourceA.notifyAll();
                    System.out.println("ThreadC notified");
                }
            }
        });
        threadA.start();
        threadB.start();
        Thread.sleep(100);
        threadC.start();
    }
}
