
import java.util.regex.Pattern;

public class Regex {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("SET.*");
        System.out.println(pattern.matcher("set('1','2')").matches());

    }
}
