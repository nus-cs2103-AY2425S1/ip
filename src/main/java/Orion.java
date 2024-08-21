import java.util.ArrayList;
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
    private static ArrayList<Task> tasks;
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
            String task = String.format("%d. %s", i + 1, Orion.tasks.get(i));
            Orion.printIndent(task);
        }
    }

    private static void addTask(Task task) {
        Orion.tasks.add(task);
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
        Task task = Orion.tasks.get(taskNo);
        task.setDone();
        Orion.printIndent("Sure! I've marked the following task as done:");
        Orion.printIndent(task.toString());
    }

    // taskNo is 0 indexed
    private static void unmarkTask(int taskNo) {
        Task task = Orion.tasks.get(taskNo);
        task.setUndone();
        Orion.printIndent("Sure! I've marked the following task as undone:");
        Orion.printIndent(task.toString());
    }

    private static void deleteTask(int taskNo) {
        Task task = Orion.tasks.get(taskNo);
        Orion.tasks.remove(task);
        Orion.noTasks--;
        Orion.printIndent("Sure! I've deleted the following task:");
        Orion.printIndent(task.toString());
        Orion.printIndent("Now you have " + Orion.noTasks + " tasks in your list.");
    }

    private static void obey(String input) {
        try {
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
                        throw new IllegalArgumentException("Correct syntax: mark <task number>");
                    } else {
                        try {
                            int taskNo = Integer.parseInt(inputArray[1]);
                            if (taskNo < 1) {
                                throw new IllegalArgumentException("Please provide a positive task number!");
                            } else if (taskNo > Orion.noTasks) {
                                String errorMsg = String.format("Number of tasks: %d. Unable to mark task %d as done.", Orion.noTasks, taskNo);
                                throw new IllegalArgumentException(errorMsg);
                            } else {
                                Orion.markTask(taskNo - 1);
                                break;
                            }
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Correct syntax: mark <task number>");
                        }
                    }
                case "unmark":
                    if (inputArray.length != 2) {
                        throw new IllegalArgumentException("Correct syntax: unmark <task number>");
                    } else {
                        try {
                            int taskNo = Integer.parseInt(inputArray[1]);
                            if (taskNo < 1) {
                                throw new IllegalArgumentException("Please provide a positive task number!");
                            } else if (taskNo > Orion.noTasks) {
                                String errorMsg = String.format("Number of tasks: %d. Unable to mark task %d as undone.", Orion.noTasks, taskNo);
                                throw new IllegalArgumentException(errorMsg);
                            } else {
                                Orion.unmarkTask(taskNo - 1);
                                break;
                            }
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Correct syntax: unmark <task number>");
                        }
                    }
                case "todo":
                    if (inputArray.length < 2) {
                        throw new IllegalArgumentException("Correct syntax: todo <task>");
                    } else {
                        Orion.addTodo(Orion.removeFirstWordFromString(input).trim());
                        break;
                    }
                case "deadline":
                    if (parsed.length != 2 || !parsed[1].matches("^by.*$")) {
                        throw new IllegalArgumentException("Correct syntax: deadline <task> /by <deadline>");
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
                        throw new IllegalArgumentException("Correct syntax: event <task> /from <start> /to <end>");
                    } else {
                        // Removes "event", "from" and "to" keywords from input
                        String[] mapped = Arrays.stream(parsed)
                                .map(Orion::removeFirstWordFromString)
                                .toArray(String[]::new);
                        Orion.addEvent(mapped[0].trim(), mapped[1].trim(), mapped[2].trim());
                        break;
                    }
                case "delete":
                    if (inputArray.length != 2) {
                        throw new IllegalArgumentException("Correct syntax: delete <task number>");
                    } else {
                        try {
                            int taskNo = Integer.parseInt(inputArray[1]);
                            if (taskNo < 1) {
                                throw new IllegalArgumentException("Please provide a positive task number!");
                            } else if (taskNo > Orion.noTasks) {
                                String errorMsg = String.format("Number of tasks: %d. Unable to delete task %d.", Orion.noTasks, taskNo);
                                throw new IllegalArgumentException(errorMsg);
                            } else {
                                Orion.deleteTask(taskNo - 1);
                                break;
                            }
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("Correct syntax: delete <task number>");
                        }
                    }
                default:
                    throw new IllegalArgumentException("Please provide a supported command!");
            }
        } catch (Exception e) {
            Orion.printIndent(e.getMessage());
        }
        Orion.printBar();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Orion.isOnline = true;
        Orion.tasks = new ArrayList<>();
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