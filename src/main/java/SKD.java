import java.util.ArrayList;
import java.util.Scanner;
public class SKD {
    private static final String LINE = "    ____________________________________________________________";
    private ArrayList<Task> tasks;

    public SKD() {
        tasks = new ArrayList<>();
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(input);
                bye();
                break;
            } else if (input.equals("list")) {
                System.out.println(input);
                returnList();
            } else if (input.startsWith("mark ")) {
                System.out.println(input);
                executeMark(input);
            } else if (input.startsWith("unmark ")) {
                System.out.println(input);
                executeUnmark(input);
            } else if (input.startsWith("todo ")) {
                System.out.println(input);
                executeToDo(input);
            } else if (input.startsWith("deadline ")) {
                System.out.println(input);
                executeDeadline(input);
            } else if (input.startsWith("event ")) {
                System.out.println(input);
                executeEvent(input);
            }
        }
        scanner.close();
    }

    private void greet() {
        System.out.println(LINE);
        System.out.println("     Hello! I'm SKD");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
        System.out.println();
    }

    private void bye() {
        System.out.println("bye");
        System.out.println(LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private void returnList() {
        System.out.println(LINE);
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("     " + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE);
    }

    private void executeMark(String input) {
        System.out.println(LINE);
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(index).markAsDone();
        System.out.println(LINE);
        System.out.println();
    }

    private void executeUnmark(String input) {
        System.out.println(LINE);
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(index).unmark();
        System.out.println(LINE);
        System.out.println();
    }

    private void executeToDo(String input) {
        System.out.println(LINE);
        String description = input.substring(5);
        ToDo task = new ToDo(description);
        tasks.add(task);
        printAddedMessage(task);
        System.out.println(LINE);
        System.out.println();
    }

    private void executeDeadline(String input) {
        System.out.println(LINE);
        String[] parts = input.substring(9).split(" /by ");
        Deadline task = new Deadline(parts[0], parts[1]);
        tasks.add(task);
        printAddedMessage(task);
        System.out.println(LINE);
        System.out.println();
    }

    private void executeEvent(String input) {
        System.out.println(LINE);
        String[] parts = input.substring(6).split(" /from | /to ");
        Event task = new Event(parts[0], parts[1], parts[2]);
        tasks.add(task);
        printAddedMessage(task);
        System.out.println(LINE);
        System.out.println();
    }

    private void printAddedMessage(Task task) {
        task.printTaskAddedMessage(tasks.size());
    }


    public static void main(String[] args) {
        new SKD().run();
    }
}
