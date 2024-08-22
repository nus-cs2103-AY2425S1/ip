import java.util.Scanner;
import java.util.Arrays;

public class SecondMind {
    private static final String line = "____________________________________________________________";
    private static final String logo = "SecondMind";
    private static Task[] taskList = new Task[100];
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

    private static Task createTask(String task) {
        String[] taskInfo = task.split(" ");
        String taskType = taskInfo[0];
        if (taskType.equals("todo")) {
            taskInfo[0] = "";
            return new ToDoTask(String.join(" ", taskInfo));
        } else {
            System.out.println("Not todo");
        }
        return new Task(task);
    }

    private static void addToTaskList(String task) {
        Task curr = createTask(task);
        taskList[taskCount] = curr;
        taskCount++;
        printLineSeparator();
        System.out.println("Got it. I have added the following task:\n\t" + curr);
        printLineSeparator();
    }

    private static void printTaskList() {
        printLineSeparator();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(String.format("%d. %s", i+1, taskList[i]));
        }
        printLineSeparator();
    }

    private static void markAsDone(int taskNumber) {
        Task curr = taskList[taskNumber-1];
        curr.markAsDone();
        printLineSeparator();
        System.out.println("Well done! You have completed the following task:");
        System.out.println(curr);
        printLineSeparator();
    }

    private static void markAsUndone(int taskNumber) {
        Task curr = taskList[taskNumber-1];
        curr.markAsUndone();
        printLineSeparator();
        System.out.println("I've marked the following task as incomplete:");
        System.out.println(curr);
        printLineSeparator();
    }

    private static boolean processInput(String input) {
        String[] newInput = input.split(" ");
        String command = newInput[0];
        if (command.equals("bye")) {
            return true;
        } else if (command.equals("mark")) {
            markAsDone(Integer.parseInt(newInput[1]));
        } else if (command.equals("unmark")) {
            markAsUndone(Integer.parseInt(newInput[1]));
        } else if (command.equals("list")) {
            printTaskList();
        } else {
            addToTaskList(input);
        }
        return false;
    }

    private static void getInput() {
        Scanner reader = new Scanner(System.in);
        while (true) {
            boolean exit = processInput(reader.nextLine());
            if (exit) {
                break;
            }
        }
        reader.close();
    }

    private static void exitProgram() {
        printLineSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printLineSeparator();
    }

    public static void main(String[] args) {
        greetUser();
        getInput();
        exitProgram();
    }
}
