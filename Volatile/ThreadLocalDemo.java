

class Msg {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

class MsgConsumer {
    public void print() {
        System.out.println(Thread.currentThread().getName() + ",msg=" + MsgUtil.get().getMsg());
    }
}

class MsgUtil {
    public static Msg msg;
    // threadLocal线程同步，一个只能存一个，一次只能取一个
    private static ThreadLocal<Msg> threadLocal = new ThreadLocal<>();

    public static Msg get() {
        return threadLocal.get();
    }

    public static void set(Msg msg) {
        threadLocal.set(msg);
        // threadLocal.remove();
    }

}

/**
 * @author dayu
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        new Thread(() -> {
            Msg msg = new Msg();
            msg.setMsg("aaaaaaaa");
            // MsgUtil.msg = msg;//使用该方法，会导致线程不同步
            MsgUtil.set(msg);//使用ThreadLocal同步数据
            new MsgConsumer().print();
        }, "user A").start();
        new Thread(() -> {
            Msg msg = new Msg();
            msg.setMsg("bbbbbbbb");
            // MsgUtil.msg = msg;
            MsgUtil.set(msg);
            new MsgConsumer().print();
        }, "user B").start();
    }

}