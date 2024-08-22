import java.util.Objects;
import java.util.Scanner;
public class Danny {
    public static void main(String[] args) {
        String seperator = "____________________________________________________________";
        System.out.println(seperator);
        System.out.println("Hello! I'm Danny");
        System.out.println("What can I do for you?");
        System.out.println(seperator);
        Scanner input = new Scanner(System.in);
        String in = input.next();
        while(!Objects.equals(in, "bye")){
            System.out.println(in);
            in = input.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(seperator);
        return;
    }

}
