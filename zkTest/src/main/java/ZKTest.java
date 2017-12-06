import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;

public class ZKTest {



    public static void main(String[] args) throws InterruptedException, IOException, KeeperException {
        ZKTest zkTest = new ZKTest();
        zkTest.init();
        zkTest.setCreateWatch(rootPath ,zkCli1);

        zkCli1.create(rootPath,"test".getBytes(),new ArrayList<ACL>(){
            {
                add(new ACL());
            }
        }, CreateMode.EPHEMERAL);
        zkTest.close();
    }

    static String connectString = "10.240.132.13:2181,10.240.132.13:5";

    static String rootPath = "/mongo-connector";
    static ZooKeeper zkCli1;
    static ZooKeeper zkCli2;
    static ZooKeeper zkCli3;

    public void init() throws IOException {
        zkCli1 = new ZooKeeper("", 30 * 1000, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("session 1 watcher triggered ,zk state:" + event.getState() + ",event type:" + event.getType() + ",path:" + event.getPath());
            }
        });
        zkCli2 = new ZooKeeper("", 30 * 1000, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("session 2 watcher triggered ,zk state:" + event.getState() + ",event type:" + event.getType() + ",path:" + event.getPath());
            }
        });
        zkCli3 = new ZooKeeper("", 30 * 1000, new Watcher() {
            public void process(WatchedEvent event) {
                System.out.println("session 3 watcher triggered ,zk state:" + event.getState() + ",event type:" + event.getType() + ",path:" + event.getPath());
            }
        });
    }

    public void close() throws InterruptedException {
        zkCli1.close();
        zkCli2.close();
        zkCli3.close();
    }

    /**
     * 给指定节点设置一个watch，该watch在该节点被create时触发
     */
    public void setCreateWatch(String path,ZooKeeper zkCli) throws KeeperException, InterruptedException {
        //node create和delete时候触发
        zkCli.exists(path, true);
    }

    /**
     * 给指定节点设置一个watch，该watch在该节点被create时触发
     */
    public void setDataChangedWatch(String path,ZooKeeper zkCli) throws KeeperException, InterruptedException {
        /*
         *triggered by a successful operation that sets data on the node, or
         * deletes the node.*/
        zkCli.getData(path,true,new Stat());
    }

    /**
     * 给指定节点设置一个watch，该watch在该节点被create时触发
     */
    public void setChildrenChangedWatch(String path,ZooKeeper zkCli) throws KeeperException, InterruptedException {
        /*triggered when deletes the node of the given path or creates/delete a child under the node.*/
        zkCli.getChildren(path, true);
    }

    /**
     * 给指定节点设置一个watch，该watch在该节点被create时触发
     */
    public void setDeletedWatch(String path,ZooKeeper zkCli) throws KeeperException, InterruptedException {
        /*triggered when deletes the node of the given path or creates/delete a child under the node.*/
        zkCli.getChildren(path, true);
    }


}
