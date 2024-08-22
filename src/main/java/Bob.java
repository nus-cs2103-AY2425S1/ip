import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Bob {
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String GREETINGS = "Hello! I'm Bob\n"
            + "What can I do for you?\n";
    private static final String EXIT = "Bye. Hope to see you again soon!\n";
    public static void main(String[] args) {

        System.out.print(DIVIDER + GREETINGS + DIVIDER);

        chatBot();

        System.out.print(EXIT + DIVIDER);
    }

    public static void chatBot() {
        Scanner scanner = new Scanner(System.in);
        List<Task> myTasks = new ArrayList<>();
        while (true) {
            String input = scanner.next();
            System.out.print(DIVIDER);
            switch (input.split(" ")[0]) {
                case "bye":
                    return;
                case "list":
                    listTasks(myTasks);
                    break;
                case "mark":
                    markTask(myTasks.get(scanner.nextInt() - 1));
                    break;
                case "unmark":
                    unmarkTask(myTasks.get(scanner.nextInt() - 1));
                    break;
                case "todo":
                    myTasks.add(newToDo(scanner.nextLine()));
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                case "deadline":
                    myTasks.add(newDeadline(scanner.nextLine()));
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                case "event":
                    myTasks.add(newEvent(scanner.nextLine()));
                    System.out.printf(" Now you have %d tasks in the list.%n", myTasks.size());
                    break;
                default:
                    System.out.print("Please enter a valid command\n");
            }
            System.out.print(DIVIDER);
        }
    }

    public static Task printAddTask(Task task) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("\t" + task);
        return task;
    }
    public static Task newToDo(String input) {
        return printAddTask(new ToDos(input));
    }

    public static Task newDeadline(String input) {
        String[] inputs = input.split("/by");
        return printAddTask(new Deadlines(inputs[0], inputs[1]));
    }

    public static Task newEvent(String input) {
        String[] inputs = input.split("/from");
        String[] dates = inputs[1].split("/to");
        return printAddTask(new EventTask(inputs[0], dates[0], dates[1]));
    }

    public static void markTask(Task task) {
        task.mark();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println(" " + task);
    }
    public static void unmarkTask(Task task) {
        task.unmark();
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println(" " + task);
    }

    public static void listTasks(List<Task> myTasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 1; i < myTasks.size() + 1; i++) {
            System.out.print(" " + i + "." + myTasks.get(i-1) + "\n");
        }
    }
}