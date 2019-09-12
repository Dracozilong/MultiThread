package FutureTask;

import java.util.Random;
import java.util.Timer;
import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) throws InterruptedException, ExecutionException {


        Callable<Integer> callable=new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(10000);
                return new Random().nextInt();
            }
        };
        FutureTask<Integer> futureTask= new FutureTask<Integer>(callable);
        new Thread(futureTask).start();

        try {
            Thread.sleep(1000);
            System.out.println("hello begin");
            System.out.println(futureTask.isDone());
            System.out.println(futureTask.get());
            System.out.println(futureTask.isDone());
            System.out.println("hello end");
        }catch (InterruptedException e){
            e.printStackTrace();

        }

    }
}
