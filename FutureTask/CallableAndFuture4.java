package FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture4 {
    public static void main(String[] args) {
        //创建一个线程池
        ExecutorService exectors = Executors.newFixedThreadPool(5);

        //定义三个Callable
        Callable<String> callable1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                return "我是Call1的返回值";
            }
        };

        Callable<String> callable2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "我是Call2的返回值";
            }
        };

        Callable<String> callable3 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "我是Call3的返回值";
            }
        };

        Future<String> future1 = exectors.submit(callable1);
        Future<String> future2 = exectors.submit(callable2);
        Future<String> future3 = exectors.submit(callable3);
        //开始获取返回值
        System.out.println("两个任务提交完毕，开始获取结果 ");
    }

}
