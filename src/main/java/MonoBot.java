import java.util.Scanner;
import java.util.ArrayList;

public class MonoBot {

    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        MonoBot.printGreeting();

        Scanner sc = new Scanner(System.in);
        Boolean keepAlive = true;

        while (keepAlive) {
            String input = sc.nextLine();
            String[] task = input.split(" ", 2);
            Command command = getCommand(task[0]);

            switch (command) {
                case LIST:
                    MonoBot.printTasks();
                    break;

                case TODO:
                    Task todo = new Todo(task[1]);
                    MonoBot.addTask(todo);
                    break;

                case DEADLINE:
                    String[] deadlineDetails = task[1].split("/by", 2);
                    Task deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                    MonoBot.addTask(deadline);
                    break;

                case EVENT:
                    String[] eventDetails = task[1].split("/from|/to ", 3);
                    Task event = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
                    MonoBot.addTask(event);
                    break;

                case MARK:
                    int indexToMark = Integer.parseInt(task[1]) - 1;
                    MonoBot.markTask(indexToMark);
                    break;

                case UNMARK:
                    int indexToUnmark = Integer.parseInt(task[1]) - 1;
                    MonoBot.unmarkTask(indexToUnmark);
                    break;

                case INVALID:
                    System.out.println("Invalid Command");
                    break;

                case BYE:
                    sc.close();
                    keepAlive = false;
                    break;
            }
        }
        MonoBot.printFarewell();
    }

    private static void hLine() {
        System.out.println("―――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
    }

    private static void printGreeting() {
        MonoBot.hLine();
        System.out.println(" Hello! I'm MonoBot");
        System.out.println(" What can I do for you?");
        MonoBot.hLine();
    }

    private static void printFarewell() {
        MonoBot.hLine();
        System.out.println(" Bye. Hope to see you again soon!");
        MonoBot.hLine();
    }

    public static Command getCommand(String str) {
        String command = str.toLowerCase();
        return switch (command) {
            case "list" -> Command.LIST;
            case "mark" -> Command.MARK;
            case "unmark" -> Command.UNMARK;
            case "todo" -> Command.TODO;
            case "deadline" -> Command.DEADLINE;
            case "event" -> Command.EVENT;
            case "bye" -> Command.BYE;
            default -> Command.INVALID;
        };
    }

    private static void addTask(Task task) {
        MonoBot.taskList.add(task);
        MonoBot.hLine();
        System.out.println("Added: " + task);
        System.out.println("Now you have " + MonoBot.taskList.size() + " task(s) in the list");
        MonoBot.hLine();
    }

    private static void printTasks() {
        MonoBot.hLine();
        if (MonoBot.taskList.isEmpty()) {
            System.out.println("No tasks added yet");
        } else {
            System.out.println("Here are the tasks in your list");
            for (int i = 0; i < MonoBot.taskList.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, MonoBot.taskList.get(i));
            }
        }
        MonoBot.hLine();
    }

    private static void markTask(int i) {
        Task curr = MonoBot.taskList.get(i);
        curr.markTask();
        MonoBot.hLine();
        System.out.println("Nice! I have marked this task as completed:\n" + curr);
        MonoBot.hLine();
    }

    private static void unmarkTask(int i) {
        Task curr = MonoBot.taskList.get(i);
        curr.unmarkTask();
        MonoBot.hLine();
        System.out.println("Ok! I have marked this task as incomplete:\n" + curr);
        MonoBot.hLine();
    }
}

/**
 * todo sleep
 * event 2101 tutorial /from 9am /to 12pm
 * deadline ip week 2 /by friday 4pm
 * list
 * mark 3
 * mark 2
 * list
 * unmark 2
 * list
 * bye
 */
