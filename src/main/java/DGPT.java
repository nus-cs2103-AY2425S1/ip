import java.util.Scanner;

public class DGPT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        // Starting Message
        System.out.println("-----------------------");
        System.out.println("DGPT: Hello! I'm DGPT");
        System.out.println("DGPT: What can I do for you?");
        System.out.println("-----------------------");

        do {
            System.out.print("User: ");
            input = scanner.nextLine();

            if (!input.equals("bye")) {
                System.out.println("-----------------------");
                System.out.println("DGPT: " + input);
                System.out.println("-----------------------");
            }
        } while (!input.equals("bye"));

        scanner.close();

        // Closing Message
        System.out.println("-----------------------");
        System.out.println("DGPT: Bye. Hope to see you again soon!");
        System.out.println("-----------------------");
    }
}
