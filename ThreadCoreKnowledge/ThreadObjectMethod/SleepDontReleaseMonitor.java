package ThreadCoreKnowledge.ThreadObjectMethod;

//展示sleep的时候不释放锁 ,等sleep时间到了以后才会释放锁
public class SleepDontReleaseMonitor implements Runnable {

    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor = new SleepDontReleaseMonitor();
        new Thread(()->sleepDontReleaseMonitor.syn(),"thread1").start();
        new Thread(()->sleepDontReleaseMonitor.syn(),"thread2").start();
    }
    @Override
    public void run() {
       syn();
    }
    private synchronized void syn(){
        System.out.println("线程"+Thread.currentThread().getName()+"获取到了锁");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+Thread.currentThread().getName()+"推出了代码块");
    }
}
