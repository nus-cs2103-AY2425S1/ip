import java.io.IOException;
import java.util.Scanner;

public class Denim {
    static final String filePath = "data/denim.txt";
    private static final TaskManager TASK_MANAGER = new TaskManager();
    private static final TaskIO TASK_IO = new TaskIO(filePath);
    private static final TaskList TASK_LIST = new TaskList();
    static final String horizontalLine = "____________________________________________________________";
    static final String chatBotName = "Denim";

    public static void main(String[] args) {
        displayGreetingMessage();

        try {
            TASK_IO.readTaskData(TASK_LIST);
        } catch (IOException e) {
            System.out.println("Unable to read denim.txt");
            return;
        } catch (DenimException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Scans User Input in the CLI
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            TASK_MANAGER.processInput(TASK_LIST, input);
            input = sc.nextLine();
        }
        displayExitMessage();
        sc.close();
    }

    private static void displayGreetingMessage() {
        String greetingMessage = String.format("%s%n Hello! I'm %s!%n What can I do for you? %n%s%n",
                horizontalLine, chatBotName, horizontalLine);
        System.out.println(greetingMessage);
    }

    public static void displayExitMessage() {
        String byeMessage = String.format("%s%n %s%n%s", horizontalLine, "Bye. Hope to see you again soon!",
                horizontalLine);
        System.out.println(byeMessage);
    }

    public static void displayDeleteMessage(Task deletedTask, TaskList taskList) {
        String deleteMessage = String.format("%s%nGot it. I've deleted this task:%n   " +
                        "%s%nNow you have %d tasks in the list.%n%s%n", horizontalLine,
                deletedTask, taskList.getTaskListSize(), horizontalLine);
        System.out.println(deleteMessage);
    }

    public static void displayMarkMessage(TaskList taskList, int index) {
        System.out.printf("Okay, I've marked this task as done: \n %s\n", taskList.getTask(index));
    }

    public static void displayUnmarkMessage(TaskList taskList, int index) {
        System.out.printf("Okay, I've marked this task as not done yet: \n %s\n", taskList.getTask(index));
    }

    public static void displayTaskAdditionMessage(TaskList taskList, Task task) {
        System.out.printf("%s%nGot it. I've added this task:%n   %s%nNow you have %d tasks in the list.%n%s%n",
                horizontalLine, task, taskList.getTaskListSize(), horizontalLine);
    }

    public static void displayErrorMessage(Exception e) {
        String errorMessage = String.format("%s%n I don't Understand what you mean T.T!\n Error: %s%n%s",
                horizontalLine, e.getMessage(), horizontalLine);
        System.out.println(errorMessage);
    }

    public static void displayListMessage(TaskList taskList) {
        taskList.enumerateList();
    }
}


