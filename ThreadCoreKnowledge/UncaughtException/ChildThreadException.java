package ThreadCoreKnowledge.UncaughtException;
// 子线程发出异常
public class ChildThreadException implements Runnable{
    public static void main(String[] args) {
        new Thread(new ChildThreadException()).start();
        for (int i=0;i<1000;i++){
            System.out.println(i);
        }
    }
    @Override
    public void run() {
        throw new RuntimeException();
    }
}
