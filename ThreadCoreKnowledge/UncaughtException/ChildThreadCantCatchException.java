package ThreadCoreKnowledge.UncaughtException;
//不加trycatch抛出4个异常，都带线程名称
//线程异常不能用trycatch捕获
public class ChildThreadCantCatchException implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        ChildThreadCantCatchException exception =new ChildThreadCantCatchException();
        try {
            new Thread(()->exception.run(),"MyThread-1").start();
            Thread.sleep(3000);
            new Thread(()->exception.run(),"MyThread-2").start();
            Thread.sleep(3000);
            new Thread(()->exception.run(),"MyThread-3").start();
            Thread.sleep(3000);
            new Thread(()->exception.run(),"MyThread-4").start();
            Thread.sleep(3000);
        }catch (RuntimeException e){
            System.out.println("Caught Exception");
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            throw  new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("Exception Caught");
        }

    }
}
