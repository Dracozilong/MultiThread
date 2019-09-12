package FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableAndFuture2  implements Callable<Integer>{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
      CallableAndFuture2 callableAndFuture2= new CallableAndFuture2();
        FutureTask<Integer> futureTask=new FutureTask<>(callableAndFuture2);
        new Thread(futureTask,"有返回值的线程").start();
        System.out.println("子线程的返回值"+futureTask.get());
    }

    @Override
    public Integer call() throws Exception {
        int i;
        for ( i = 0; i < 10; i+=2) {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
        return i;
    }
}
