import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CancelGPT {
    private final String CHATBOT_NAME;
    private final List<Task> TASKS_LIST;

    public CancelGPT() {
        this.CHATBOT_NAME = "CancelGPT";
        this.TASKS_LIST = new ArrayList<>();
    }
    public static void main(String[] args) {
        CancelGPT cancelGPT = new CancelGPT();
        cancelGPT.run();
    }

    public void run() {
        String horizontalLine = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);

        System.out.println(horizontalLine);
        greet();
        System.out.println(horizontalLine);

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(horizontalLine);
            handleCommand(command);
            System.out.println(horizontalLine);
            command = sc.nextLine();
        }

        sc.close();
        System.out.println(horizontalLine);
        exit();
        System.out.println(horizontalLine);
    }

    public void greet() {
        System.out.println("Hello! I am " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Good bye. Hope to see you again soon!");
    }

    public void handleCommand(String command) {
        if (command.equals("list")) {
            displayTasksList();
        } else if (command.startsWith("mark")) {
            String[] commandArray = command.split(" ");
            int taskNumber = Integer.parseInt(commandArray[1]);
            markTaskNumber(taskNumber);
        } else if (command.startsWith("unmark")) {
            String[] commandArray = command.split(" ");
            int taskNumber = Integer.parseInt(commandArray[1]);
            unmarkTaskNumber(taskNumber);
        } else if (command.startsWith("todo")) {
            String[] commandArray = command.split(" ");
            String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, commandArray.length);
            String taskDescription = String.join(" ", taskDescriptionArr);
            handleAddingTask(new ToDo(taskDescription));
        } else if (command.startsWith("deadline")) {
            String[] commandArray = command.split(" ");
            int byIndex = Arrays.asList(commandArray).indexOf("/by");
            String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, byIndex);
            String taskDescription = String.join(" ", taskDescriptionArr);
            String[] byDateArr = Arrays.copyOfRange(commandArray, byIndex + 1, commandArray.length);
            String byDate = String.join(" ", byDateArr);
            handleAddingTask(new Deadline(taskDescription, byDate));
        } else if (command.startsWith("event")) {
            String[] commandArray = command.split(" ");
            int fromIndex = Arrays.asList(commandArray).indexOf("/from");
            int toIndex = Arrays.asList(commandArray).indexOf("/to");
            String[] taskDescriptionArr = Arrays.copyOfRange(commandArray, 1, fromIndex);
            String taskDescription = String.join(" ", taskDescriptionArr);
            String[] fromDateArr = Arrays.copyOfRange(commandArray, fromIndex + 1, toIndex);
            String fromDate = String.join(" ", fromDateArr);
            String[] toDateArr = Arrays.copyOfRange(commandArray, toIndex + 1, commandArray.length);
            String toDate = String.join(" ", toDateArr);
            handleAddingTask(new Event(taskDescription, fromDate, toDate));
        }
    }

    public void handleAddingTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + addToTaskList(task));
        System.out.println("Now you have " + this.TASKS_LIST.size() + " tasks in the list.");
    }

    public void markTaskNumber(int taskNumber) {
        this.TASKS_LIST.get(taskNumber - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + this.TASKS_LIST.get(taskNumber - 1));
    }

    public void unmarkTaskNumber(int taskNumber) {
        this.TASKS_LIST.get(taskNumber - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + this.TASKS_LIST.get(taskNumber - 1));
    }

    public String addToTaskList(Task task) {
        this.TASKS_LIST.add(task);
        return task.toString();
    }

    public void displayTasksList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.TASKS_LIST.size(); i++) {
            System.out.println(i + 1 + ". " + this.TASKS_LIST.get(i));
        }
    }
}
