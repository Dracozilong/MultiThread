package ThreadCoreKnowledge.StopThread.VolatileDemo;

public class WrongWayVolatile implements Runnable {

    private volatile boolean cancel = false;

    @Override
    public void run() {
        try {
        int num = 0;
        while (num<=10000 && !cancel){
           if (num%100==0){
               System.out.println(num+"是100的倍数");
           }
           num++;
              Thread.sleep(1);
           }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile=new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        Thread.sleep(5000);
        wrongWayVolatile.cancel=true;
    }
}
