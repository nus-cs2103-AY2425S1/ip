import java.util.Scanner;

public class TheOrangeRatchetCat {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm TheOrangeRatchetCat");
        System.out.println("What can I do for you?");
        System.out.println("What can I do for you?");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Reads a line of text
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
            input = scanner.nextLine(); // Reads the next line of input text again
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        scanner.close(); // Close the scanner to avoid resource leaks
    }
}
