package ThreadCoreKnowledge.MultiThreadError;
//第二种线程安全问题 ，演示死锁
public class DeadLockError implements Runnable {

    int flag = 1;
    static Object o1 = new Object();
    static Object o2 = new Object();

    public static void main(String[] args) {
        DeadLockError deadLockError1 =new DeadLockError();
        DeadLockError deadLockError2 =new DeadLockError();
        deadLockError1.flag=1;
        deadLockError2.flag=0;
        new Thread(()->deadLockError1.run(),"deadLockError1").start();
        new Thread(()->deadLockError2.run(),"deadLockError2").start();
    }

    @Override
    public void run() {
        System.out.println("flag="+flag);
        if (flag==1){
            synchronized (o1){
                try {
                    Thread.sleep(600);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("1");
                }
            }
        }
        if (flag==0){
            synchronized (o2){
                try {
                    Thread.sleep(600);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("0");
                }
            }
        }
    }
}
