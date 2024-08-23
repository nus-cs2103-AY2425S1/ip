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
            } else if (userCommand.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                Task task = list.get(taskIndex);
                task.mark();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task);
            } else if (userCommand.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                Task task = list.get(taskIndex);
                task.unmark();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(task);
            } else if (userCommand.startsWith("todo")) {
                Task task = new ToDo(userCommand);
                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (userCommand.startsWith("event")) {
                String[] commandParts = userCommand.split("/from|/to");
                Task task = new Event(commandParts[0], commandParts[1], commandParts[1]);
                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (userCommand.startsWith("deadline")) {
                String[] commandParts = userCommand.split("/by");
                Task task = new Deadline(commandParts[0], commandParts[1]);
                list.add(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else {
                System.out.println("nothing");
            }
        }

        String goodbyeMessage = "Bye. Hope to see you again soon!";
        System.out.println(line + "\n" + goodbyeMessage + "\n" + line);
        scanner.close();
    }
}
