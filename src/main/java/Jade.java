import java.util.Scanner;
import java.util.ArrayList;

public class Jade {
    public static void main(String[] args) {
        String indent = "     "; // 5 spaces for indentation
        String topLine = "    " + "_".repeat(60) + "\n";
        String botLine = "\n" + "    " + "_".repeat(60);
        String greet = indent + "Hello! I'm Jade!\n"
                + indent + "What can I do for you?";
        String exit = indent + "Bye. Hope to see you again soon!";
        String listMessage = indent + "Here are the tasks in your list:";
        ArrayList<Task> tasks = new ArrayList<>();
        String message;

        System.out.println(topLine + greet + botLine);

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(topLine + listMessage);
                for (int i = 0; i < tasks.size(); i++) {
                    if (i < tasks.size() - 1) {
                        System.out.println(indent + (i + 1) + "." + tasks.get(i));
                    } else {
                        // last task
                        System.out.println(indent + (i + 1) + "." + tasks.get(i)
                                + botLine);
                    }
                }
                command = sc.nextLine();
            } else if (command.startsWith("mark ")) {
                int taskIndex = Integer.parseInt(command.substring(5)) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsDone();
                    message = indent + "Nice! I've marked this task as done:"
                            + "\n" + indent + "  " + tasks.get(taskIndex);
                } else {
                    message = indent + "Hmm, no such task. Try again.";
                }
                System.out.println(topLine + message + botLine);
                command = sc.nextLine();
            } else if (command.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(command.substring(7)) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markAsNotDone();
                    message = indent + "OK, I've marked this task as not done yet:"
                            + "\n" + indent + "  " + tasks.get(taskIndex);
                } else {
                    message = indent + "Hmm, no such task. Try again.";
                }
                System.out.println(topLine + message + botLine);
                command = sc.nextLine();
            } else {
                Task newTask = new Task(command);
                tasks.add(newTask);

                message = indent + "added: " + command;
                System.out.println(topLine + message + botLine);
                command = sc.nextLine();
            }
        }

        System.out.println(topLine + exit + botLine);
    }
}
