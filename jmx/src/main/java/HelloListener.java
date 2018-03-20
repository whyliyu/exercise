import javax.management.Notification;
import javax.management.NotificationListener;

public class HelloAgent implements NotificationListener {

    public void handleNotification(Notification notification, Object handback) {
        System.out.println(notification.getMessage());
        System.out.println(notification.getUserData());
        System.out.println(handback);
    }
}
