import java.util.Scanner;

public class Orion {
    static Scanner scanner = new Scanner(System.in);
    static String horizontalLine = "────────────────────────────────────────";

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Orion");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
        System.out.println("\n");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;  // Exit the loop if the user types "bye"
            }
            System.out.println(horizontalLine);
            System.out.println(input);
            System.out.println(horizontalLine);
        }

        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
