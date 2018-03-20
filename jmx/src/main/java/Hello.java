import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Hello extends NotificationBroadcasterSupport implements HelloMBean {
    String greetings = "hello";
    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        try {
            Notification notification = new Notification("justtest",this,-1,System.currentTimeMillis(),greetings);
            sendNotification(notification);
            this.greetings = greetings;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("set");
        }

    }

    public void setObject(Object object) {
        Object o = object;
    }

    public void sayHello() {
        System.out.println(greetings);
        throw new RuntimeException("ex");
    }
}
