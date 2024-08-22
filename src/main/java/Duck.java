import java.util.ArrayList;
import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        String line = "____________________________________________________________";
        String welcomeMessage = "Hello! I'm Duck\nWhat can I do for you?";
        System.out.println(line + "\n" + welcomeMessage + "\n" + line);

        while (true) {
            String userCommand = scanner.nextLine();
            String[] commandParts = userCommand.split(" ", 2);
            if (userCommand.equals("bye")) {
                break;
            }
            if (userCommand.equals("list")) {
                System.out.println(line + "\nHere are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    int num = i + 1;
                    System.out.println(num + ". " + list.get(i));
                }
                System.out.println(line);
            } else if (commandParts.length == 2 && commandParts[0].equals("mark")) {
                int taskIndex = Integer.parseInt(commandParts[1]) - 1;
                Task task = list.get(taskIndex);
                task.mark();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task);
            } else if (commandParts.length == 2 && commandParts[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(commandParts[1]) - 1;
                Task task = list.get(taskIndex);
                task.unmark();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(task);
            } else {
                Task task = new Task(userCommand);
                list.add(task);
                System.out.println(line + "\n" + "added: " + userCommand + "\n" + line);
            }
        }

        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(line + "\n" + goodbyeMessage + "\n" + line);
        scanner.close();
    }
}
