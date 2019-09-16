package ThreadCoreKnowledge.Singleton;
//饿汉式(静态常量)（可用）
public class HungryType {

    private final static HungryType INSTANCE = new HungryType();

    private HungryType() {

    }
    public static HungryType getInstance(){
        return INSTANCE;
    }
}
