import java.util.Scanner;

public class Chatsy {
    static final String name = "Chatsy";
    static final String line = "\t____________________________________________________________";
    static final Scanner scanner = new Scanner(System.in);

    static final String[] tasks = new String[100];
    static int taskCount = 0;

    public static void greet() {
        System.out.println(line);
        System.out.println("\tHello! I'm " + name + "\n\tWhat can I do for you?");
        System.out.println(line);
    }

    public static void output(String output) {
        System.out.println(line);
        System.out.println(output);
        System.out.println(line);
    }

    public static void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void addTask(String task) {
        if (taskCount < tasks.length) {
            tasks[taskCount] = task;
            taskCount++;
            output("\tadded: " + task);
        } else {
            output("\tTask list is full!");
        }
    }

    public static void listTasks() {
        if (taskCount == 0) {
            output("\tNo tasks to list.");
        } else {
            StringBuilder listOutput = new StringBuilder("\n");
            for (int i = 0; i < taskCount; i++) {
                listOutput.append("\t").append(i + 1).append(". ").append(tasks[i]).append("\n");
            }
            output(listOutput.toString());
        }
    }

    public static void nextCommand() {
        String command = scanner.nextLine();

        if (command.equals("bye")) {
            exit();
        } else if (command.equals("list")) {
            listTasks();
            nextCommand();
        } else {
            addTask(command);
            nextCommand();
        }
    }

    public static void main(String[] args) {
        greet();
        nextCommand();
    }
}
