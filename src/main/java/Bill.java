import java.util.Scanner;

public class Bill {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bill");
        System.out.println("What can I do for you?");

        Scanner userScanner = new Scanner(System.in);
        String userCommand = userScanner.nextLine();

        while (!userCommand.equals("bye")) {
            System.out.println(userCommand);
            userCommand = userScanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
