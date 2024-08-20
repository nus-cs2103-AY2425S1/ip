import java.util.Objects;
import java.util.Scanner;

public class Blob {
    private static boolean active = true;
    public static void main(String[] args) {
        Scanner human = new Scanner(System.in);
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm Blob");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");

        while (active) {
            String action = human.nextLine();
            System.out.println("______________________________________________");
            if (Objects.equals(action.toLowerCase(), "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("______________________________________________");
                active = false;
            } else {
                System.out.println(action);
                System.out.println("______________________________________________");
            }
        }
    }
}
