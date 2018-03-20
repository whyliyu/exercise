
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","root");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select action_xml from COORD_ACTIONS");
        while(resultSet.next()) {
            Blob blob = resultSet.getBlob(1);
            GZIPInputStream gzIn = new GZIPInputStream(blob.getBinaryStream());
            Scanner scanner = new Scanner(gzIn);
            while(scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        }

    }
}
