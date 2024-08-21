import java.util.Arrays;
import java.util.Scanner;
public class Orion {

    public static final String LOGO = "             .__               \n"
                                    + "  ___________|__| ____   ____  \n"
                                    + " /  _ \\_  __ \\  |/  _ \\ /    \\ \n"
                                    + "(  <_> )  | \\/  (  <_> )   |  \\\n"
                                    + " \\____/|__|  |__|\\____/|___|  /\n"
                                    + "                            \\/ \n";

    public static final String BAR = "______________________________________________\n";

    public static final String INDENT = "    ";

    private static boolean isOnline;
    private static Task[] tasks;
    private static int noTasks;

    private static void printBar() {
        System.out.println(Orion.BAR);
    }

    private static void printIndent(String message) {
        System.out.println(Orion.INDENT + message);
    }

    private static String removeFirstWordFromString(String str) {
        return str.split(" ", 2)[1];
    }

    private static void greet() {
        Orion.printBar();
        System.out.println(Orion.LOGO);
        Orion.printBar();

        Orion.printIndent("Hello from Orion!");
        Orion.printIndent("What do you want to talk about today?");
        Orion.printBar();
    }

    private static void sayGoodbye() {
        Orion.printIndent("Bye! Hope to see you again soon!");
    }

    private static void list() {
        Orion.printIndent("Here are the tasks in your list:");
        for (int i = 0; i < Orion.noTasks; i++) {
            String task = String.format("%d. %s", i + 1, Orion.tasks[i]);
            Orion.printIndent(task);
        }
    }

    private static void addTask(Task task) {
        Orion.tasks[Orion.noTasks] = task;
        Orion.noTasks++;
        Orion.printIndent("Sure! I've added the following task to your list:");
        Orion.printIndent(task.toString());
        Orion.printIndent("Now you have " + Orion.noTasks + " tasks in your list.");
    }

    private static void addTodo(String input) {
        Task task = new Todo(input);
        Orion.addTask(task);
    }

    private static void addDeadline(String name, String deadline) {
        Task task = new Deadline(name, deadline);
        Orion.addTask(task);
    }

    private static void addEvent(String name, String from, String to) {
        Task task = new Event(name, from, to);
        Orion.addTask(task);
    }

    // taskNo is 0 indexed
    private static void markTask(int taskNo) {
        Task task = Orion.tasks[taskNo];
        task.setDone();
        Orion.printIndent("Sure! I've marked the following task as done:");
        Orion.printIndent(task.toString());
    }

    // taskNo is 0 indexed
    private static void unmarkTask(int taskNo) {
        Task task = Orion.tasks[taskNo];
        task.setUndone();
        Orion.printIndent("Sure! I've marked the following task as undone:");
        Orion.printIndent(task.toString());
    }

    private static void obey(String input) {
        String[] inputArray = input.split(" ");
        String command = inputArray[0].toLowerCase();
        // For use by deadline and event commands
        String[] parsed = input.split("/");
        switch (command) {
            case "bye":
                Orion.isOnline = false;
                Orion.sayGoodbye();
                break;
            case "list":
                Orion.list();
                break;
            case "mark":
                if (inputArray.length != 2) {
                    Orion.printIndent("Correct syntax: mark <task number>");
                    break;
                } else if (Integer.parseInt(inputArray[1]) > Orion.noTasks) {
                    int taskNo = Integer.parseInt(inputArray[1]);
                    String errorMsg = String.format("Number of tasks: %d. Unable to mark task %d as done.", Orion.noTasks, taskNo);
                    Orion.printIndent(errorMsg);
                    break;
                } else {
                    int taskNo = Integer.parseInt(inputArray[1]);
                    Orion.markTask(taskNo - 1);
                    break;
                }
            case "unmark":
                if (inputArray.length != 2) {
                    Orion.printIndent("Correct syntax: unmark <task number>");
                    break;
                } else if (Integer.parseInt(inputArray[1]) > Orion.noTasks) {
                    int taskNo = Integer.parseInt(inputArray[1]);
                    String errorMsg = String.format("Number of tasks: %d. Unable to mark task %d as undone.", Orion.noTasks, taskNo);
                    Orion.printIndent(errorMsg);
                    break;
                } else {
                    int taskNo = Integer.parseInt(inputArray[1]);
                    Orion.unmarkTask(taskNo - 1);
                    break;
                }
            case "todo":
                if (inputArray.length != 2) {
                    Orion.printIndent("Correct syntax: todo <task>");
                    break;
                } else {
                    Orion.addTodo(inputArray[1]);
                    break;
                }
            case "deadline":
                if (parsed.length != 2 || !parsed[1].matches("^by.*$")) {
                    Orion.printIndent("Correct syntax: deadline <task> /by <deadline>");
                    break;
                } else {
                    // Removes "deadline" and "by" keywords from input
                    String[] mapped = Arrays.stream(parsed)
                                            .map(Orion::removeFirstWordFromString)
                                            .toArray(String[]::new);
                    Orion.addDeadline(mapped[0].trim(), mapped[1].trim());
                    break;
                }
            case "event":
                if (parsed.length != 3 || !parsed[1].matches("^from.*$") || !parsed[2].matches("^to.*$")) {
                    Orion.printIndent("Correct syntax: event <task> /from <start> /to <end>");
                    break;
                } else {
                    // Removes "event", "from" and "to" keywords from input
                    String[] mapped = Arrays.stream(parsed)
                            .map(Orion::removeFirstWordFromString)
                            .toArray(String[]::new);
                    Orion.addEvent(mapped[0].trim(), mapped[1].trim(), mapped[2].trim());
                    break;
                }
            default:
                Orion.printIndent("Please provide a supported command!");
        }
        Orion.printBar();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Orion.isOnline = true;
        Orion.tasks = new Task[100];
        Orion.noTasks = 0;
        Orion.greet();

        while (Orion.isOnline) {
            String command = sc.nextLine();
            Orion.printBar();
            Orion.obey(command);
        }

        sc.close();
    }
}