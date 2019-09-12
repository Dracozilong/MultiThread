package Volatile;

public class VolatileDemo {
    private volatile  int number=0;

    public  int getNumber(){
        return this.number;
    }

    public void  increase(){
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.number++;
    }

    public static void main(String[] args) {
        final  VolatileDemo voldemo=new VolatileDemo();
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    voldemo.increase();
                }
            }).start();
        }

        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println("number:"+voldemo.getNumber());
    }
}
