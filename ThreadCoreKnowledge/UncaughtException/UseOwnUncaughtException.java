package ThreadCoreKnowledge.UncaughtException;

public class UseOwnUncaughtException implements Runnable {
    public static void main(String[] args) throws InterruptedException {

            Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器1"));
            new Thread(new UseOwnUncaughtException(),"MyThread-1").start();
            Thread.sleep(3000);
            new Thread(new UseOwnUncaughtException(),"MyThread-2").start();
            Thread.sleep(3000);
            new Thread(new UseOwnUncaughtException(),"MyThread-3").start();
            Thread.sleep(3000);
            new Thread(new UseOwnUncaughtException(),"MyThread-4").start();
            Thread.sleep(3000);

    }
    @Override
    public void run() {
            throw  new RuntimeException();
    }
}
