package ThreadCoreKnowledge.SixStates;
//展示这三种状态
public class BlockedWatingTimedWaiting implements Runnable {
    public static void main(String[] args) throws InterruptedException {
      BlockedWatingTimedWaiting runnable = new BlockedWatingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        Thread.sleep(200);
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
    }

    @Override
    public void run() {
       syn();
    }

    private synchronized void  syn(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
