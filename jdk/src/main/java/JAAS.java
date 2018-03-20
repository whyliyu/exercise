import javax.security.auth.Subject;
import java.lang.reflect.Field;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

public class JAAS {

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalAccessException {
        printCtx();
        Subject subject = new Subject();
        subject.getPrincipals().add(new Principal() {
            @Override public String getName() {
                return "lyh";
            }
        });
        Subject.doAs(subject, new PrivilegedAction<Void>() {
            @Override
            public Void run() {
                privilegedCode();
                return null;
            }
        });
//        System.out.println(subject.getPrincipals());
    }

    static void printCtx() throws IllegalAccessException {
        AccessControlContext context = AccessController.getContext();
        System.out.println(context);
        Subject subject = Subject.getSubject(context);
        System.out.println("subject:" + subject);

        Field[] fields = AccessControlContext.class.getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            Object val = field.get(context);
            System.out.println(field.getType() + " " + field.getName() + ":" + val);
            if(field.getName().equals("context")) {
                ProtectionDomain[] protectionDomains = (ProtectionDomain[]) val;
                for(ProtectionDomain protectionDomain : protectionDomains) {
                    System.out.println("hash:" + protectionDomain.hashCode());
                    System.out.println(protectionDomain.toString());

                }
            }
            System.out.println("--------------");
        }



    }

    //only invoke by doPrivilegedAction
    static void privilegedCode() {
        System.out.println();
        System.out.println("*********start**************");
        System.out.println("this is privileged code");
        try {
            printCtx();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("---------end--------------");
    }
}
