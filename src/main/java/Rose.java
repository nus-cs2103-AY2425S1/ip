import java.util.Scanner;
import java.util.ArrayList;

public class Rose {
    static final String horizontal = "____________________________________________________________";
    static final String name = "Rose";
    static ArrayList<Task> tasks = new ArrayList<Task>();

    private static void printIndented(String line) {
        System.out.println("    " + line);
    }
    private static void addTask(String taskType, String taskName) {
        try {
            Task newTask;

            if (taskType.equals("event")) {
                String[] partsA = taskName.split(" /from ");
                if (partsA.length < 2) {
                    throw new RoseException("Event task is missing '/from'.");
                }
                String[] partsB = partsA[1].split(" /to ");
                if (partsB.length < 2) {
                    throw new RoseException("Event task is missing '/to'.");
                }
                newTask = new Event(partsA[0], partsB[0], partsB[1]);
            } else if (taskType.equals("deadline")) {
                String[] parts = taskName.split(" /by ");
                if (parts.length < 2) {
                    throw new RoseException("Deadline task is missing '/by'.");
                }
                newTask = new Deadline(parts[0], parts[1]);
            } else {
                newTask = new Todo(taskName);
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String opening = horizontal + "\n    Hi gorgeous! I'm " + name + "~~\n"
                + "    How can I help you today?\n    " + horizontal;
        String closing = horizontal + "\n    See you~~ Don't forget to drink some water ^_^\n    " + horizontal;

        printIndented(opening);

        String input = scanner.nextLine();
        String message = "";

        while (!input.equals("bye")) {
            String command = input.split(" ",2)[0].toLowerCase();
            message = "";
            if ((input.split(" ", 2).length > 1)) {
                message = input.split(" ", 2)[1];
            }

            try {
                if (command.equals("list")) {
                    showList();
                } else if (command.equals("mark")) {
                    markTask(Integer.valueOf(message));
                } else if (command.equals("unmark")) {
                    unmarkTask(Integer.valueOf(message));
                } else if (command.equals("todo") || command.equals("event") || command.equals("deadline")) {
                    if (message.isEmpty()) {
                        throw new RoseException(String.format("You need to put more details after the '%s' command",
                                command));
                    }
                    addTask(command, message);
                } else {
                    throw new RoseException("I'm sorry, but I don't know that command :-(");
                }
            } catch (RoseException e) {
                printIndented(horizontal);
                printIndented("OOPS!!! " + e.getMessage());
                printIndented(horizontal);
            } catch (NumberFormatException e) {
                printIndented(horizontal);
                printIndented("OOPS!!! You should provide a number for the task index.");
                printIndented(horizontal);
            }
            input = scanner.nextLine();
        }

        printIndented(closing);
    }
}
