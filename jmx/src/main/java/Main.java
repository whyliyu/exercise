import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnectorServer;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MBeanServer mbs = MBeanServerFactory.createMBeanServer("HelloAgent");

        HtmlAdaptorServer adaptor = new HtmlAdaptorServer();
        adaptor.setPort(9092);
        ObjectName adapterName = null;

        RMIConnectorServer rmiAdapter = new RMIConnectorServer(new JMXServiceURL("rmi",null,9093), null);
        ObjectName rmiAdapterName = null;

        Hello hello = new Hello();
        hello.addNotificationListener(new HelloListener(),null,null);
        ObjectName helloName = null;

        try {
            helloName = new ObjectName("HelloAgent:name=hello1");
            mbs.registerMBean(hello,helloName);

            adapterName = new ObjectName("HelloAgent:name=helloAdapter,port=9092");
            mbs.registerMBean(adaptor,adapterName);
            adaptor.start();

            rmiAdapterName = new ObjectName("HelloAgent1:label=rmi");
            mbs.registerMBean(rmiAdapter,rmiAdapterName);
            rmiAdapter.start();

        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NotCompliantMBeanException e) {
            e.printStackTrace();
        } catch (InstanceAlreadyExistsException e) {
            e.printStackTrace();
        } catch (MBeanRegistrationException e) {
            e.printStackTrace();
        }

    }
}
