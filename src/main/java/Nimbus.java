import java.util.Scanner;

public class Nimbus {
    private static String name = "Nimbus";
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static void printDash() {
        System.out.println("____________________________________________________________");
    }

    public static void printWelcomeMessage() {
        printDash();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printDash();
    }

    public static void printGoodbyeMessage() {
        printDash();
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }

    public static void addTask(Task task) {
        tasks[taskCount++] = task;
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public static void setDone(int x) {
        System.out.println("Nice! I've marked this task as done:");
        tasks[x].setDone();
        System.out.println(tasks[x]);
    }

    public static void setNotDone(int x) {
        System.out.println("OK, I've marked this task as not done yet:");
        tasks[x].setNotDone();
        System.out.println(tasks[x]);
    }

    public static void printAllTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; ++i) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }

    public static void invalidCommand() {
        System.out.println("Invalid Command");
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String line;

        while(!(line = scanner.nextLine()).equals("bye")) {
            String text;
            String command = "";
            int index = line.indexOf(" ");
            if (index == -1) {
                switch (line) {
                    case "list":
                        printAllTask();
                        break;
                    default:
                        invalidCommand();
                }
            } else {
                command = line.substring(0, index);
                text    = line.substring(index + 1);
                if (text == "") {
                    invalidCommand();
                }
                switch (command) {
                    case "list":
                        printAllTask();
                        break;
                    case "mark":
                        setDone(Integer.parseInt(text) - 1);
                        break;
                    case "unmark":
                        setNotDone(Integer.parseInt(text) - 1);
                        break;
                    case "todo":
                        addTask(new Todo(text));
                        break;
                    case "deadline": {
                        String byArg = "/by";
                        int byIndex = text.indexOf(byArg);
                        String description = text.substring(0, byIndex);
                        String deadline = text.substring(byIndex + byArg.length());
                        addTask(new Deadline(description.trim(), deadline.trim()));
                        break;
                    }
                    case "event": {
                        String fromArg = "/from";
                        String toArg = "/to";
                        int fromIndex = text.indexOf(fromArg);
                        int toIndex = text.indexOf(toArg);
                        String description = text.substring(0, fromIndex);
                        String from = text.substring(fromIndex + fromArg.length(), toIndex);
                        String to = text.substring(toIndex + toArg.length());
                        addTask(new Event(description.trim(), from.trim(), to.trim()));
                        break;
                    }
                    default:
                        invalidCommand();
                }
            }
        }

        printGoodbyeMessage();
    }
}
