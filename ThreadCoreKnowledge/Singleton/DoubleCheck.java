package ThreadCoreKnowledge.Singleton;

import java.util.function.DoublePredicate;

//双重检查 (推荐面试使用)
public class DoubleCheck {

    private  volatile static DoubleCheck instance;

    private DoubleCheck(){

    }

    public static DoubleCheck getInstance(){
        if (instance==null){
            synchronized (DoubleCheck.class){
                if (instance == null){
                    instance = new DoubleCheck();
                }
            }
        }
        return instance;
    }
}
