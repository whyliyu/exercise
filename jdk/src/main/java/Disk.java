
import java.io.File;

public class Disk {

    public static void main(String[] args) {
        File[] roots = File.listRoots();

        for(File file : roots) {
            System.out.println(file.getFreeSpace());
        }
        File file = new File("d:/test");
        System.out.println(file.getFreeSpace() + "/" + file.getTotalSpace()+ "," + (file.getFreeSpace() + 0.0)/file.getTotalSpace());
    }
}
