package ExecutorPool.SingleThreadExecutor;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Yao  implements Runnable{

            private String name;

            public Yao(String name) {
                this.name = name;
            }

            public static void foo(Yao d){
                d= new Yao("feifei");
    }

    @Override
    public void run() {
      int temp = 0 ;
      int i = 0;
      Random random = new Random();
      while (true){
          int j = random.nextInt(100);
          System.err.println("temp----------->"+temp+"  i---------------->"+(i++)+"    j------------------>"+j);

          try{
              if(temp==0&&j>90) temp = 7/0;
              Thread.currentThread().sleep(10);

          }catch(Exception e) {
              e.printStackTrace();
              temp = 1;
          }

      }

    }

    public static void main(String[] args) {

        Yao yao = new Yao("max");
        foo(yao);
        System.out.println("aDog.name equals Max ="+yao.name.equals("Max"));
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        singleThreadExecutor.submit(yao);
    }

}

