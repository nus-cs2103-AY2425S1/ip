import java.util.Scanner;

public class Crack {
    public static void main(String[] args) {
        String greeting = "____________________________________________________________\n"
                + " Hello! I'm Crack\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("You: ");
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Crack says " + input);
            }
        }

        scanner.close();
    }
}
