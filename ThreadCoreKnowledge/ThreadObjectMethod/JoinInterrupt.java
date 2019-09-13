package ThreadCoreKnowledge.ThreadObjectMethod;

public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("Thread1 finished.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"主线程中断");
            e.printStackTrace();
        }
        System.out.println("子线程运行完毕");
    }
}
