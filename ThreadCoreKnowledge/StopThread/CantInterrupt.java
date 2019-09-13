package ThreadCoreKnowledge.StopThread;

//响应中断以后 ，标记位被清除了
public class CantInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable =()->{
            int num = 0;
            while (num<=10000){
                if(num % 100==0){
                    System.out.println(num+"是100的倍数");
                }
                num++;
                try {
                 Thread.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
