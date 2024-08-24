import java.util.Scanner;
import java.util.ArrayList;

public class Rose {
    static final String horizontal = "____________________________________________________________";
    static final String name = "Rose";
    static ArrayList<Task> tasks = new ArrayList<Task>();

    private static void printIndented(String line) {
        System.out.println("    " + line);
    }
    private static void addTask(TaskType taskType, String taskName) {
        try {
            Task newTask;

            switch (taskType) {
                case EVENT:
                    String[] partsA = taskName.split(" /from ");
                    if (partsA.length < 2) {
                        throw new RoseException("Event task is missing '/from'.");
                    }
                    String[] partsB = partsA[1].split(" /to ");
                    if (partsB.length < 2) {
                        throw new RoseException("Event task is missing '/to'.");
                    }
                    newTask = new Event(partsA[0], partsB[0], partsB[1]);
                    break;
                case DEADLINE:
                    String[] parts = taskName.split(" /by ");
                    if (parts.length < 2) {
                        throw new RoseException("Deadline task is missing '/by'.");
                    }
                    newTask = new Deadline(parts[0], parts[1]);
                    break;
                case TODO:
                    newTask = new Todo(taskName);
                    break;
                default:
                    throw new RoseException("Unknown task type.");
            }

            tasks.add(newTask);
            printIndented(horizontal);
            printIndented("Got it. I've added this task: ");
            printIndented("  " + newTask.toString());
            printIndented(String.format("Now you have %d task in the list.", tasks.size()));
            printIndented(horizontal);

        } catch (RoseException e) {
            printIndented(horizontal);
            printIndented("OOPS!!! " + e.getMessage());
            printIndented(horizontal);
        }
    }

    private static void showList() {
        printIndented(horizontal);
        for (int i = 1; i <= tasks.size(); i++) {
            printIndented(i + ". " + tasks.get(i - 1).toString());
        }
        printIndented(horizontal);
    }

    private static void markTask(Integer idx) {
        try {
            tasks.get(idx - 1).mark();
            //Task updatedTask = tasks.get(idx - 1).mark();
            //tasks.set(idx - 1, updatedTask);
            printIndented(horizontal);
            printIndented("Marked as done : ");
            printIndented(tasks.get(idx - 1).toString());
            printIndented(horizontal);
        } catch (IndexOutOfBoundsException e) {
            printIndented(horizontal);
            printIndented("OOPS!!! Task index is out of bounds.");
            printIndented(horizontal);
        }
    }

    private static void unmarkTask(Integer idx) {
        try {
            tasks.get(idx - 1).unmark();
            printIndented(horizontal);
            printIndented("Marked as not done :");
            printIndented(tasks.get(idx - 1).toString());
            printIndented(horizontal);
        } catch (IndexOutOfBoundsException e) {
            printIndented(horizontal);
            printIndented("OOPS!!! Task index is out of bounds.");
            printIndented(horizontal);
        }
    }

    private static void delete(Integer idx) {
        try {
            Task deleted = tasks.get(idx - 1);
            tasks.remove(idx - 1);
            printIndented(horizontal);
            printIndented("Noted. I've removed this task:");
            printIndented("  " + deleted.toString());
            printIndented(String.format("Now you have %d task in the list.", tasks.size()));
            printIndented(horizontal);
        } catch (IndexOutOfBoundsException e) {
            printIndented(horizontal);
            printIndented("OOPS!!! Task index is out of bounds.");
            printIndented(horizontal);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String opening = horizontal + "\n    Hi gorgeous! I'm " + name + "~~\n"
                + "    How can I help you today?\n    " + horizontal;
        String closing = horizontal + "\n    See you~~ Don't forget to drink some water ^_^\n    " + horizontal;

        printIndented(opening);

        String[] input = scanner.nextLine().split(" ",2);
        String message = "";

        while (!input[0].equals("bye")) {
            String command = input[0];
            message = (input.length > 1) ? input[1] : "";

            try {
                switch (command) {
                    case "list":
                        showList();
                        break;
                    case "mark":
                        markTask(Integer.valueOf(message));
                        break;
                    case "unmark":
                        unmarkTask(Integer.valueOf(message));
                        break;
                    case "todo":
                        if (message.isEmpty()) {
                            throw new RoseException("You need to provide details for the TODO task.");
                        }
                        addTask(TaskType.TODO, message);
                        break;
                    case "event":
                        if (message.isEmpty()) {
                            throw new RoseException("You need to provide details for the EVENT task.");
                        }
                        addTask(TaskType.EVENT, message);
                        break;
                    case "deadline":
                        if (message.isEmpty()) {
                            throw new RoseException("You need to provide details for the DEADLINE task.");
                        }
                        addTask(TaskType.DEADLINE, message);
                        break;
                    case "delete":
                        delete(Integer.valueOf(message));
                        break;
                    default:
                        printIndented(horizontal);
                        printIndented("OOPS!!! I'm sorry, but I don't know that command :-(");
                        printIndented(horizontal);
                }
            } catch (RoseException e) {
                printIndented(horizontal);
                printIndented("OOPS!!! " + e.getMessage());
                printIndented(horizontal);
            } catch (NumberFormatException e) {
                printIndented(horizontal);
                printIndented("OOPS!!! You should provide a number of the task index.");
                printIndented(horizontal);
            }
            input = scanner.nextLine().split(" ",2);
        }

        printIndented(closing);
    }
}
