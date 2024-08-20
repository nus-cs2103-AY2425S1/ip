import java.util.Objects;
import java.util.Scanner;

public class Assistinator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String command = "";
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm Assistinator");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");

        while(!Objects.equals(command, "bye")) {
            command = input.nextLine();
            System.out.println("______________________________________________");
            if (!Objects.equals(command, "bye")) {
                System.out.println(command);
            } else {
                System.out.println("Bye. Hope to see you again soon!");
            }
            System.out.println("______________________________________________");
        }
    }
}
