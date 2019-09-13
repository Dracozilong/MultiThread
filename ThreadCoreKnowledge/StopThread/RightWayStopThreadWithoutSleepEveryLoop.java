package ThreadCoreKnowledge.StopThread;
//如果在执行过程中 每次循环都会sleep或者wait
public class RightWayStopThreadWithoutSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable =()->{
            int num =0;
            try {
                    while (num <= 10000) {
                        if (num % 100 == 0) {
                            System.out.println(num + "是100的倍数");
                        }
                    num++;
                    Thread.sleep(10);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
