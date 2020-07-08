public class SynchronizedTest {
   private volatile int balance;

   public SynchronizedTest(int initialBalance)
   {
      balance = initialBalance;
   }

   // public synchronized boolean withdraw(int amount) {
   //    if (amount <= balance) {
   //       try {
   //          Thread.sleep((int) (Math.random() * 200));
   //       } catch (InterruptedException ie) {
   //       }
   //       balance -= amount;
   //       return true;
   //    }
   //    return false;
   // }

   public static void main(String[] args) {
      final SynchronizedTest ca = new SynchronizedTest(100);
      Runnable r = new Runnable() {
         SynchronizedTest st=ca;
         @Override
         public void run() {
            String name = Thread.currentThread().getName();
            boolean result=false;
            // for (int i = 0; i < 5; i++)
            while(true){

               synchronized (this) {
                  if(st.balance==0) break;
                  if (10 <= st.balance) {
                     try {
                        Thread.sleep((int) (Math.random() * 200));
                     } catch (InterruptedException ie) {
                     }
                     st.balance -= 10;
                     result=true;
                  }else{
                     result=false;
                  }
                  System.out.println(name + " withdraws $10: " + result);
               }
         }
      }
      };
      Thread thdHusband = new Thread(r);
      thdHusband.setName("Husband");
      Thread thdWife = new Thread(r);
      thdWife.setName("Wife");
      thdHusband.start();
      thdWife.start();
   }
}