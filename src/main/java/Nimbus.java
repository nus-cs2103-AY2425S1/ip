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
        System.out.println("added: " + task.getDescription());
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
                        // throws error
                }
            } else {
                command = line.substring(0, index);
                text    = line.substring(index + 1);
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
                    default:
                        addTask(new Task(line));
                }
            }
        }

        printGoodbyeMessage();
    }
}
