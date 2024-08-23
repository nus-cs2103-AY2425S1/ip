import java.util.Scanner;

public class ArsenBot {
    public static void main(String[] args) {
        System.out.println("Hello! I'm ArsenBot");
        System.out.println("What can I do for you?\n");

        Scanner reader = new Scanner(System.in);
        while (true) {
            String input = reader.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            }

            System.out.println(input);
        }
    }
}
