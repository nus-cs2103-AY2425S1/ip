import java.util.Objects;
import java.util.Scanner;

public class BuddyBot {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("__________________________");
        System.out.println(" Hello! I'm BuddyBot");
        System.out.println(" What can I do for you?");
        String input = myObj.nextLine();
        if (Objects.equals(input, "bye")) {
            System.out.println(" Bye, Hope to see you again soon1");
        } else {
            System.out.println(input);
        }
    }
}
