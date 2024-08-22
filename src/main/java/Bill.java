import java.util.Scanner;
import java.util.ArrayList;

public class Bill {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Bill");
        System.out.println("What can I do for you?");

        ArrayList<Task> userList = new ArrayList<>();
        Scanner userScanner = new Scanner(System.in);
        String userCommand = userScanner.nextLine();

        while (!userCommand.equals("bye")) {
            if (userCommand.equals("list")) {
                if (userList.isEmpty()) {
                    System.out.println("List is empty");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println((i + 1) + "." + "[" + userList.get(i).getStatusIcon() + "] " + userList.get(i).getDescription());
                    }
                }
            } else {
                String[] parsedInput = userCommand.split(" ");
                if (parsedInput[0].equals("mark")) {
                    if (Integer.parseInt(parsedInput[1]) > userList.size() || Integer.parseInt(parsedInput[1]) < 1) {
                        System.out.println("There is no task of that number in the current list");
                    } else {
                        System.out.println("Nice! I've marked this task as done:");
                        Task targetTask = userList.get(Integer.parseInt(parsedInput[1]) - 1);
                        targetTask.mark();
                        System.out.println("[" + targetTask.getStatusIcon() + "] " + targetTask.getDescription());
                    }
                } else if (parsedInput[0].equals("unmark")) {
                    if (Integer.parseInt(parsedInput[1]) > userList.size() || Integer.parseInt(parsedInput[1]) < 1) {
                        System.out.println("There is no task of that number in the current list");
                    } else {
                        System.out.println("OK, I've marked this task as not done yet:");
                        Task targetTask = userList.get(Integer.parseInt(parsedInput[1]) - 1);
                        targetTask.unmark();
                        System.out.println("[" + targetTask.getStatusIcon() + "] " + targetTask.getDescription());
                    }
                } else {
                    userList.add(new Task(userCommand));
                    System.out.println("added: " + userCommand);
                }
            }
            userCommand = userScanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
