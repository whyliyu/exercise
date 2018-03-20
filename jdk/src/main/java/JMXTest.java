import com.sun.jmx.mbeanserver.JmxMBeanServer;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Date;

public class JMXTest {

    public static void main(String[] args){
        System.out.println(new Date(1514359728000L));
    }
}

interface JmxMBean {

    String getName();

    void setName(String name);

    void printHello();
}

class Jmx implements JmxMBean {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void printHello() {
        System.out.println("hello");
    }
}
