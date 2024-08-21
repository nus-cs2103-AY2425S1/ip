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

    private static void addTask(String input) {
        Task task = new Task(input);
        Orion.tasks[Orion.noTasks] = task;
        Orion.noTasks++;
        Orion.printIndent("Sure! I've added the following task to your list:");
        Orion.printIndent(task.toString());
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
        switch (command) {
            case "bye":
                Orion.isOnline = false;
                Orion.sayGoodbye();
                break;
            case "list":
                Orion.list();
                break;
            case "mark":
                if (inputArray[1] == null) {
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
                if (inputArray[1] == null) {
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
            default:
                Orion.addTask(input);
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