package ThreadCoreKnowledge.Singleton;

//饿汉式(静态代码块)（可用）
public class HungryTypeCodeBlock {
    private final static HungryTypeCodeBlock INSTANCE ;

    static {
        INSTANCE =new HungryTypeCodeBlock();
    }

    private HungryTypeCodeBlock() {

    }
    public static HungryTypeCodeBlock getInstance(){
        return INSTANCE;
    }
}
