import java.util.Scanner;
import java.util.ArrayList;

public class Bill {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bill");
        System.out.println("What can I do for you?");

        ArrayList<String> userList = new ArrayList<String>();
        Scanner userScanner = new Scanner(System.in);
        String userCommand = userScanner.nextLine();

        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                if (userList.isEmpty()) {
                    System.out.println("List is empty");
                } else {
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println((i + 1) + ". " + userList.get(i));
                    }
                }
            } else {
                userList.add(userCommand);
                System.out.println("added: " + userCommand);
            }
            userCommand = userScanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
