
import java.lang.reflect.Type;

public class Reflect {

    static class Interface<T1> {

    }

    static class Clazz<T1> extends Interface<T1> {

    }

    static class Clazz2 extends Clazz<String>{

    }



    public static void main(String[] args) {
        Class<?> c = Clazz2.class;

        Type type = c.getGenericSuperclass();

        System.out.println(type.getTypeName());

        c = c.getSuperclass();

        type = c.getGenericSuperclass();

        System.out.println(type.getTypeName());


    }
}
