package FutureTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureCook {

    //  用厨具烹饪食材
    static void cook(Chuju chuju, Shicai shicai) {}

    // 厨具类
    static class Chuju {}

    // 食材类
    static class Shicai {}
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        //第一步网购厨具
        Callable<Chuju> onlineShopping=new Callable<Chuju>() {
            @Override
            public Chuju call() throws Exception {
                System.out.println("第一步：下单");
                System.out.println("第一步：等待送货");
                Thread.sleep(5000); //模拟送货时间
                System.out.println("第一步，快递送到");
                return  new Chuju();
            }
        };

        FutureTask<Chuju> futureTask = new FutureTask<>(onlineShopping);
        new Thread(futureTask).start();
        //去超市购买食材
        Thread.sleep(2000);//模拟购买食材的时间
        Shicai shicai =new Shicai();
        System.out.println("第二步 食材到位");
        //第三步，用厨具烹饪食材
        if (!futureTask.isDone()){
            System.out.println("第三步：厨具还没到，心情好就等着（心情不好就调用cancel方法取消订单）");
        }
        Chuju chuju = futureTask.get();
        System.out.println("厨具到位开始展示 厨艺");
        cook(chuju,shicai);
        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");

    }
}
