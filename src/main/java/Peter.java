import java.util.Scanner;

public class Peter {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Peter\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().strip();

        while (!command.equals("bye")) {
            System.out.println(command);
            command = scanner.nextLine();
        }

        // EXIT
        System.out.println("\nBye, hope to see you again soon!");
        scanner.close();
    }
}
