import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by liyuhang1 on 2017/12/14.
 */
public class SemaphoreTest {

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    readWriteLock.readLock().lock();
                    System.out.println("test");
                    readWriteLock.readLock().unlock();
                }

            }
        }).start();
        readWriteLock.writeLock().lock();
        Condition condition = readWriteLock.writeLock().newCondition();
        condition.awaitUninterruptibly();
        readWriteLock.writeLock().unlock();
    }
}
