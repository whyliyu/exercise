import java.util.Date;

public class UnicodeTest {

    public static void main(String[] args) {
        System.out.println(Character.toChars(Integer.parseInt("1D306",16))[0] + 1);
//        Character.codePointAt()

        System.out.println(new Date(1513597807000L));
    }

}
