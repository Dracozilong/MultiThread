package ThreadCoreKnowledge.DeadLock;
// 转账的时候遇到了死锁,一旦打开注释，就会发生死锁
public class TransferMoneyAdvanced implements Runnable{

    int flag = 1;

    static Account a =new Account(500);
    static Account b =new Account(500);
    static Object lock = new Account(500);
    public static void main(String[] args) throws InterruptedException {
       TransferMoneyAdvanced r1 = new TransferMoneyAdvanced();
       TransferMoneyAdvanced r2 = new TransferMoneyAdvanced();

       r1.flag=1;

       r2.flag=0;
        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("a的余额"+a.balance);
        System.out.println("b的余额"+b.balance);

    }
    @Override
    public void run() {
      if (flag ==1){
        transferMoney(a,b,200);
      }
      if (flag == 0){
          transferMoney(b,a,200);
      }
    }

     public static void transferMoney(Account from, Account to, int amount) {
        class  Helper{
            public void transfer(){
                if (from.balance-amount<0){
                    System.out.println("余额不足");
                }
                from.balance -= amount;
                to.balance+=amount;
                System.out.println("转账成功"+amount);
            }
        }
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if(fromHash<toHash){
            synchronized (from){
                synchronized (to){
                    new Helper().transfer();
                }
            }
        }

       else if (fromHash>toHash){
            synchronized (from){
                synchronized (to){
                    new Helper().transfer();
                }
            }
        }else {
           synchronized (lock){
               synchronized (to){
                   synchronized (from){
                       new Helper().transfer();
                   }
               }
           }
        }
    }

    static class Account{

        int balance;

        public Account(int balance) {
            this.balance = balance;
        }
    }
}
