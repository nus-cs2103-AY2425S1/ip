import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Arona {
    public static void main(String[] args) {
        // For user input
        Scanner in = new Scanner(System.in);

        // Greeting when Opened
        print("Hello! I'm Arona.");
        print("What can I do for you?");

        // Process inputs
        while (true) {
            String input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            print(input);
        }
        print("Bye. Hope to see you again soon!");
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static void print(Integer lines) {
        for (int i = 0; i < lines; i++) {
            System.out.println();
        }
    }
}