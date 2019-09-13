package ThreadCoreKnowledge.ThreadObjectMethod;

public class JoinThreadState {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(mainThread.getState());
                    System.out.println("Thread -0 运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println("等待子线程运行结束");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程运行结束");
    }
}
