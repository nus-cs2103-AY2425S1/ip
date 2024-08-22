import java.util.Scanner;

public class Colby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = "  ____      _ _           \n"
                    + " / ___|___ | | |__  _   _ \n"
                    + "| |   / _ \\| | '_ \\| | | |\n"
                    + "| |__| (_) | | |_) | |_| |\n"
                    + "\\____\\___/|_|_.__/ \\__, /\n"
                    + "                   |___/ \n";

        System.out.println("Hello! I'm \n"+ logo + "\n" + "What can I do for you?\n");
        while (true) {
            String task = scanner.nextLine();

            if (task.equalsIgnoreCase("bye")) {
                System.out.println("  Bye bye! Hope to see you again soon! :)");
                System.out.println("_________________________________________________");
                break;
            }
            System.out.println("  " + task);
        }
        scanner.close();
    }
}
