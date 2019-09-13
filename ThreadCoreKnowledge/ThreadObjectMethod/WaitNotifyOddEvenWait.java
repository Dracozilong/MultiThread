package ThreadCoreKnowledge.ThreadObjectMethod;
//用wait和notify实现
public class WaitNotifyOddEvenWait {
  // 一旦拿到锁就打印
  //打印完，唤醒其他线程，自己就休眠

    private static int count;

    private static final Object lock =new Object();

    public static void main(String[] args) {
        new Thread(new TurnningRunner(),"偶数").start();
        new Thread(new TurnningRunner(),"奇数").start();
    }

    static class TurnningRunner implements Runnable{
        @Override
        public void run() {
            while (count<=100){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName()+":"+count);
                    count++;
                    lock.notify();
                    if (count<=100){
                        try {
                            //如果任务还没结束，让出锁 休眠
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
