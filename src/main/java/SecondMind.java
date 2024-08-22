import java.util.Scanner;

public class SecondMind {
    private static final String line = "____________________________________________________________";
    private static final String logo = "SecondMind";
    private static String[] taskList = new String[100];
    private static int taskCount = 0;

    private static void printLineSeparator() {
        System.out.println(line);
    }

    private static void greetUser() {
        printLineSeparator();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        printLineSeparator();
    }

    private static void addToTaskList(String task) {
        taskList[taskCount] = task;
        taskCount++;
        printLineSeparator();
        System.out.println("added: " + task);
        printLineSeparator();
    }

    private static void getInput() {
        Scanner reader = new Scanner(System.in);
        while (true) {
            String command = reader.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                addToTaskList(command);
            }
        }
        reader.close();
    }

    private static void exitProgram() {
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public static void main(String[] args) {
        greetUser();
        getInput();
        exitProgram();
    }
}
