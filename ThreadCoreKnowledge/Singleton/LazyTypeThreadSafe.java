package ThreadCoreKnowledge.Singleton;
// 懒汉式(线程安全) (不推荐)
public class LazyTypeThreadSafe {
    private static LazyTypeThreadSafe instance;

    private LazyTypeThreadSafe(){

    }

    private synchronized LazyTypeThreadSafe getInstance(){
        if(instance == null){
            instance = new LazyTypeThreadSafe();
        }
        return instance;
    }
}
