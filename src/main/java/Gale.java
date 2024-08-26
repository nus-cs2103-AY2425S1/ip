import java.util.ArrayList;
import java.util.Scanner;
public class Gale {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private ArrayList<Task> taskList;

    public Gale() {
        this.taskList = new ArrayList<>();
    }
    public static void main(String[] args) {
        Gale gale = new Gale();
        gale.run();
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskList.get(taskIndex).status()) {
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("Your task is already completed!");
                    System.out.println(HORIZONTAL_LINE);
                } else {
                    markTask(taskIndex, true);
                }
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                if (!taskList.get(taskIndex).status()) {
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("Your task is already unmarked!");
                    System.out.println(HORIZONTAL_LINE);
                } else {
                    markTask(taskIndex, false);
                }
            } else if (input.startsWith("todo")) {
                addToDo(input);
            } else if (input.startsWith("deadline")) {
                addDeadline(input);
            } else if (input.startsWith("event")) {
                addEvent(input);
            } else {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Oops, your command is not valid in this windy realm.");
                System.out.println(HORIZONTAL_LINE);
            }
        }

        scanner.close();
    }
    public void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Gale, your friendly windy assistant.");
        System.out.println("I'll keep your deadlines, to-do's and events in my memory. What do you have to do?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printAddedTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Whoosh! Task \"" + task.toString() + "\" added to my windy memory.");
        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the air.");
        }
        System.out.println("Anything else?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void addToDo(String input) {
        String description = input.substring(5).trim();
        Task task = new ToDo(description);
        taskList.add(task);
        printAddedTask(task);
    }

    public void addDeadline(String input) {
        String[] truncatedInput = input.substring(9).split("/by");
        String description = truncatedInput[0].trim();
        String by = truncatedInput[1].trim();
        Task task = new Deadline(description, by);
        taskList.add(task);
        printAddedTask(task);
    }

    public void addEvent(String input) {
        String[] truncatedInput = input.substring(6).split("/from|/to");
        String description = truncatedInput[0].trim();
        String from = truncatedInput[1].trim();
        String to = truncatedInput[2].trim();
        Task task = new Event(description, from, to);
        taskList.add(task);
        printAddedTask(task);
    }
    public void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        if (taskList.isEmpty()) {
            System.out.println("No tasks breezing around now!");
        } else {
            System.out.println("You are breezy with these tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.get(i));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }
    public void markTask(int index, boolean isDone) {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            if (isDone) {
                task.markAsDone();
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Good work! You breezed through this task!");
            } else {
                task.markAsNotDone();
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Tough, this task is back in the windy realm.");
            }
            System.out.println("  " + task.toString());
        } else {
            System.out.println("Oops! That task number is lost in the wind. Try again?");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Aw, it's time for you to go huh?");
        System.out.println("Catch you on the next gust!");
        System.out.println(HORIZONTAL_LINE);
    }
}
