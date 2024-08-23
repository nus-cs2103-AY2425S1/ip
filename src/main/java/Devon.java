import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Devon {

    protected Scanner scanner = new Scanner(System.in);
    protected ArrayList<Task> tasks = new ArrayList<>();
    protected int taskCount = 0;

    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN;

        public static Command fromString(String command) {
            try {
                return Command.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }
    }

    private void start() {
        introduction();
        receiveUserInput();
        goodbye();
    }

    private void printLongLine() {
        String LINE_SEPARATOR = "____________________";
        System.out.println("\t" + LINE_SEPARATOR);
    }

    private void introduction() {
        printLongLine();
        System.out.println("\t" + "Hello! I'm Devon.");
        System.out.println("\t" + "What can I do for you?");
        printLongLine();
    }

    private void goodbye() {
        printLongLine();
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        printLongLine();
    }

    private String detectCommand(String msg) {
        String[] parts = msg.split(" ");
        return parts[0];
    }

    private String detectContent(String msg) {
        String[] parts = msg.split(" ");
        return parts.length > 1
                ? String.join(" ", Arrays.copyOfRange(parts, 1, parts.length))
                : "Error";
    }

    private void receiveUserInput() {
        while (true) {
            String input = scanner.nextLine();
            Command command = Command.fromString(detectCommand(input));

            try {
                switch (command) {
                    case BYE:
                        return;
                    case LIST:
                        printList();
                        break;
                    case MARK:
                        markAction(input);
                        break;
                    case UNMARK:
                        unmarkAction(input);
                        break;
                    case TODO:
                        todoAction(input);
                        break;
                    case DEADLINE:
                        deadlineAction(input);
                        break;
                    case EVENT:
                        eventAction(input);
                        break;
                    case DELETE:
                        deleteAction(input);
                        break;
                    default:
                        unknownAction();
                        break;
                }
            } catch (DevonException e) {
                printLongLine();
                System.out.println("\t" + e);
                printLongLine();
            }
        }
    }

    private void markAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(detectContent(input)) - 1;
        } catch (NumberFormatException e) {
            throw new DevonInvalidTaskNumberException();
        }
        if (taskIndex < 0 || taskIndex >= taskCount) {
            throw new DevonInvalidTaskNumberException();
        }
        markAsDone(tasks.get(taskIndex));
    }

    private void unmarkAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(detectContent(input)) - 1;
        } catch (NumberFormatException e) {
            throw new DevonInvalidTaskNumberException();
        }
        if (taskIndex < 0 || taskIndex >= taskCount) {
            throw new DevonInvalidTaskNumberException();
        }
        markAsUndone(tasks.get(taskIndex));
    }

    private void todoAction(String input) {
        String description = detectContent(input).trim();
        addToList(new Todo(description));
    }

    private void deadlineAction(String input) throws DevonInvalidDeadlineException {
        String content = detectContent(input);
        if (!content.contains("/by")) {
            throw new DevonInvalidDeadlineException();
        }
        String[] parts = content.split("/by", 2);
        String description = parts[0].trim();
        String by = parts[1].trim();
        addToList(new Deadline(description, by));
    }

    private void eventAction(String input) throws DevonInvalidEventException {
        String content = detectContent(input);
        if (!(content.contains("/from") && content.contains("/to"))) {
            throw new DevonInvalidEventException();
        }
        String[] partsFrom = content.split("/from", 2);
        String[] partsTo = partsFrom[1].split("/to", 2);
        String description = partsFrom[0].trim();
        String from = partsTo[0].trim();
        String to = partsTo[1].trim();
        addToList(new Event(description, from, to));
    }

    private void deleteAction(String input) throws DevonInvalidTaskNumberException {
        int taskIndex = Integer.parseInt(detectContent(input)) - 1;
        if (taskIndex < 0 || taskIndex >= taskCount) {
            throw new DevonInvalidTaskNumberException();
        }
        deleteTask(taskIndex);
    }

    private void unknownAction() throws DevonUnknownCommandException {
        throw new DevonUnknownCommandException();
    }

    private void addToList(Task task) {
        tasks.add(task);
        taskCount++;
        this.printLongLine();
        System.out.println("\t" + "Got it. I've added this task:");
        System.out.println("\t\t" + task);
        printNumberOfTasks();
        this.printLongLine();
    }

    private void printList() {
        this.printLongLine();
        System.out.println("\t" + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            Task current = tasks.get(i);
            String formattedEntry = String.format(
                    "\t" + "%d. %s",
                    i + 1,
                    current
            );
            System.out.println(formattedEntry);
        }
        this.printLongLine();
    }

    private void printNumberOfTasks() {
        System.out.println("\t" + "Now you have " + taskCount + " tasks in the list.");
    }

    private void markAsDone(Task task) {
        printLongLine();
        task.markAsDone();
        printLongLine();
    }

    private void markAsUndone(Task task) {
        printLongLine();
        task.markAsUndone();
        printLongLine();
    }

    private void deleteTask(int taskIndex) {
        Task currentTask = tasks.get(taskIndex);
        currentTask.deleteTask();
        tasks.remove(taskIndex);
        taskCount--;
        printLongLine();
        printNumberOfTasks();
        printLongLine();
    }

    public static void main(String[] args) {
        Devon bot = new Devon();
        bot.start();
    }
}
