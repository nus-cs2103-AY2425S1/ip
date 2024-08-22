import java.util.Scanner;

public class Froggy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        String greeting = "Hello! I'm Froggy!\n"
                + "What can I do for you?\n"
                + "_______________________________\n";
        String exit = "Bye. Hope to see you again soon!\n"
                + "_______________________________\n";

        System.out.println(greeting);

        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(exit);
                break;
            }
        }

        scanner.close();
    }
}
