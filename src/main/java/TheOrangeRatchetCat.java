import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TheOrangeRatchetCat {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm TheOrangeRatchetCat");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        List<String> items = new ArrayList<>();
        int index = 1;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Reads a line of text
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (String item : items) {
                    System.out.println(index + ". " + item);
                    index++;
                }
                input = scanner.nextLine(); // Reads the next line of input text again
                continue;
            }
            System.out.println("____________________________________________________________");
            System.out.println("added " + input);
            System.out.println("____________________________________________________________");
            items.add(input);
            input = scanner.nextLine(); // Reads the next line of input text again
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        scanner.close(); // Close the scanner to avoid resource leaks
    }
}
