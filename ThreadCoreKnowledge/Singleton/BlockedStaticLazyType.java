package ThreadCoreKnowledge.Singleton;
 //    静态内部类 懒加载
public class BlockedStaticLazyType {

    //构造方法
    private BlockedStaticLazyType(){}



   private static class  BlockedStaticLazyTypeInstance{
      private static final BlockedStaticLazyType INSTANCE = new BlockedStaticLazyType();

    }

   public static BlockedStaticLazyType getInstance(){
      return BlockedStaticLazyTypeInstance.INSTANCE;
   }
}
