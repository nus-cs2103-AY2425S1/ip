import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Greeting message
        System.out.println("Hello from I am chatbot");
        System.out.println("What can I do for you?");

        // Create a scanner object to read input
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> save = new ArrayList<>(100);
        Integer index = 0;
        // Wait for the user to enter something
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            save.set(index, input);
            index += 1;
            System.out.println("add: " + input);
            // Wait for the user to enter something
            input = scanner.nextLine();
        }
        // Goodbye message
        System.out.println("Bye. Hope to see you again soon!");

        // Close the scanner
        scanner.close();
    }

}
