package ThreadCoreKnowledge.DeadLock;

import java.util.Random;

//演示活锁问题
public class LiveLock {
   static class Spoon{

       private Diner owner;

       public Spoon(Diner owner) {
           this.owner = owner;
       }

       public Diner getOwner() {
           return owner;
       }

       public void setOwner(Diner owner) {
           this.owner = owner;
       }

       public synchronized void use(){
           System.out.printf("%s has eaten!",owner.name);
       }
   }
   static  class Diner{

      private String name;

       public Diner(String name) {
           this.name = name;
           isHungry = true;
       }

       private boolean isHungry;
      public  void eatWith(Spoon spoon,Diner spouse){
          while (isHungry){
              if (spoon.owner!=this){
                  try {
                      Thread.sleep(1);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  continue;
              }
              Random random =new Random();
              if (spouse.isHungry && random.nextInt(10)<9){
                  System.out.println(name+"亲爱的"+spouse.name+"你先吃吧");
                  spoon.setOwner(spouse);
                  continue;
              }
              spoon.use();
              isHungry=false;
              System.out.println(name+":我吃完了");
              spoon.setOwner(spouse);
          }
      }

       public static void main(String[] args) {
           Diner husband = new Diner("灶门炭治郎");
           Diner wife = new Diner("香奈呼");
           Spoon spoon =new Spoon(husband);
           new Thread(new Runnable() {
               @Override
               public void run() {
                   husband.eatWith(spoon,wife);
               }
           }).start();

           new Thread(new Runnable() {
               @Override
               public void run() {
                   wife.eatWith(spoon,husband);
               }
           }).start();
       }
   }
}
