import java.util.Scanner;

public class Taskalyn {
    public static void main(String[] args) {

        // Initialising scanner
        Scanner scanner = new Scanner(System.in);

        // Greeting user
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hey! I'm Taskalyn, your personal Task Manager :)\n" +
                "    What can I do for you?\n");
        System.out.println("    ____________________________________________________________\n");

        // Conditions
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                break;
            }
            else {
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + input + "\n");
                System.out.println("    ____________________________________________________________\n");
            }
        }

    }
}
