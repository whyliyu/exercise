
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
//            try {
//                Thread.sleep(10 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                System.out.println("abnormally exit");
//            }
            int i = 0;
            while(true){
                i++;
            }

        });

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            thread.interrupt();
            System.out.println(thread.getState());
            System.out.println("exit normally");

        }));

        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2000);


    }
}
