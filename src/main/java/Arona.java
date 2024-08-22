import java.util.Scanner;
import java.util.ArrayList;

public class Arona {
    public static ArrayList<String> list = new ArrayList<>(100);

    public static void main(String[] args) {
        // For user input
        Scanner in = new Scanner(System.in);

        // Greeting when Opened
        print("Hello! I'm Arona.");
        print("What can I do for you?");

        // Process inputs
        while (true) {
            String input = in.nextLine();
            // Bye Check
            if (input.equalsIgnoreCase("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            }

            // Rest of the cases
            else if (input.equalsIgnoreCase("list")) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        print(+i + ". " + list.get(i));
                    }
                }
            } else {
                list.add(input);
                print("added " + input);
            }
        }
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