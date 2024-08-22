import java.util.Scanner;
import java.util.ArrayList;

public class Crack {
    public static void main(String[] args) {
        String divider = "____________________________________________________________\n";
        String greeting = divider
                + " Hello! I'm Crack\n"
                + " What can I do for you?\n"
                + divider;
        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<String> tasks = new ArrayList<String>();

        while (true) {
            System.out.print("You: ");
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else if (input.equals("list")) {
                System.out.println(divider);
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i) != null) {
                        System.out.println(
                                + (i + 1) + ". " + tasks.get(i) + "\n"
                                );
                    }
                }
                System.out.println(divider);
            }
            else {
                tasks.add(input);
                System.out.println(divider + "\n"
                        + "added: " + input + "\n"
                        + divider);
            }
        }

        scanner.close();
    }
}
