package ThreadCoreKnowledge.StopThread;
// 在catch子语句中调用Thread.currentThread().interrupt()来恢复设置中断状态,以便于在后续的执行中，依然能够检测到刚才发生的中断
//

public class RightWayStopThreadInProduct2 implements Runnable{

    @Override
    public void run() {
        while (true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted,程序运行结束");
                break;
            }
            reInterrupt();
        }
    }

    private void reInterrupt()  {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProduct2());
        thread.start();

        Thread.sleep(1000);
        thread.interrupt();
    }
}
