package ThreadCoreKnowledge.ThreadObjectMethod;

//两个线程交替打印奇偶数，用synchronize实现
public class WaitNotifyOddEvenSyn {

    private static int count;

    private static final Object lock =new Object();

    //创建两个线程
   public static void main(String[] args) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               while (count<100){
                   synchronized (lock){
                       if((count &1)==0){
                           System.out.println(Thread.currentThread().getName()+":"+count);
                           count++;
                       }
                   }
               }
           }
       },"偶数线程").start();

       new Thread(new Runnable() {
           @Override
           public void run() {
               while (count<100){
                   synchronized (lock){
                       if((count &1)==1){
                           System.out.println(Thread.currentThread().getName()+":"+count);
                           count++;
                       }
                   }
               }
           }
       },"奇数线程").start();
   }
    //一个只处理奇数 ，一个处理偶数（位运算)
    // 用synchronize来通信
}
