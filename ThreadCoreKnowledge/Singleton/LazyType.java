package ThreadCoreKnowledge.Singleton;
//懒汉式 线程不安全
public class LazyType {
  private static LazyType instance;

  private LazyType(){

  }
  public  static LazyType getInstance(){
      if (instance == null){
          instance = new LazyType();
      }
      return instance;
  }
}
