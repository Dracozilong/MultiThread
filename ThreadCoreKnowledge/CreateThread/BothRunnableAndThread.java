package ThreadCoreKnowledge.CreateThread;

// 想像一下结果 为什么？
public class BothRunnableAndThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("我来自Runnable接口");
            }
        }) {
            @Override
            public void run(){
                System.out.println("我来自Thread");
            }
        }.start();
    }
}
