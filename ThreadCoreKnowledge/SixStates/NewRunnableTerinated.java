package ThreadCoreKnowledge.SixStates;

//展示线程的NEW，Runnable ，Terminated
//即使正在运行，也是Runnable的状态
public class NewRunnableTerinated implements Runnable {
    public static void main(String[] args) {
        Thread thread = new Thread(new NewRunnableTerinated());
        System.out.println(thread.getState());
        thread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
