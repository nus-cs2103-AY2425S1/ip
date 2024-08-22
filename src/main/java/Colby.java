import java.util.Scanner;

public class Colby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] store = new String[100];
        int n = 0;

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
            } else if (task.equalsIgnoreCase("list")) {
                for (int i = 1; i < (n + 1); i++) {
                    System.out.println("  " + i + ". " + store[i - 1]);
                }
            } else {
                store[n] = task;
                n++;
                System.out.println("  added: " + task);
            }
        }
        scanner.close();
    }
}
