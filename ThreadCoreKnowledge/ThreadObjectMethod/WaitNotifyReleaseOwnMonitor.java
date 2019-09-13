package ThreadCoreKnowledge.ThreadObjectMethod;
//证明wait只释放当前的锁
public class WaitNotifyReleaseOwnMonitor {
    private static volatile Object resourceA = new Object();
    private static volatile Object resourceB = new Object();
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("resourceA got ");

                    synchronized (resourceB) {
                        System.out.println("resourceB got ");

                        try {
                            System.out.println("ThreadA releases resourceA lock");
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resourceA) {
                    System.out.println("threadB got resourceA lock");
                    synchronized (resourceB) {
                        System.out.println("threadB got resourceB lock");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }



}
