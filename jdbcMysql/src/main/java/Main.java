
import javax.sql.rowset.serial.SerialBlob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","root");
        PreparedStatement statement = connection.prepareStatement("update test set a=?,b=? where c=?");
        statement.setInt(1,2);
        statement.setString(2,"adf");
        statement.setBlob(3, new SerialBlob(new byte[]{5}));
        System.out.println(statement.executeUpdate());
    }
}
