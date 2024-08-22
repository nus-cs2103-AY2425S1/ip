import java.util.Objects;
import java.util.Scanner;
public class Optimus {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Optimus");
        System.out.println("What can I do for you?");
        Scanner stringScanner = new Scanner(System.in);
        while (true) {
            String text = stringScanner.next();
            if (text.equals("bye")){
                break;
            }
            System.out.println(text);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
