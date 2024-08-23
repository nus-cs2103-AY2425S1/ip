import java.util.ArrayList;
import java.util.Scanner;

public class ChatBaby {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        greet();

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                printTasks(tasks);
            } else if (input.startsWith("mark")) {
                markTask(input, tasks);
            } else if (input.startsWith("unmark")) {
                unmarkTask(input, tasks);
            } else if (input.startsWith("todo")) {
                handleTaskCommand(tasks, "todo", input, 5);
            } else if (input.startsWith("deadline")) {
                handleTaskCommand(tasks, "deadline", input, 9);
            } else if (input.startsWith("event")) {
                handleTaskCommand(tasks, "event", input, 6);
            } else {
                printUnknownCommandError();
            }
        }
    }

    public static void greet() {
        System.out.println("____________________________________________________________\n"
                + "Hello! I'm ChatBaby\n"
                + "What can I do for you?\n"
                + "____________________________________________________________");
    }

    public static void bye() {
        System.out.println("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________");
    }

    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTask(String input, ArrayList<Task> tasks) {
        try {
            int index = Integer.parseInt(input.substring(5).trim()) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).markAsDone();
                System.out.println("____________________________________________________________\n"
                        + "Nice! I've marked this task as done:\n"
                        + tasks.get(index).toString() + "\n"
                        + "____________________________________________________________");
            } else {
                printInvalidTaskIndexError();
            }
        } catch (NumberFormatException e) {
            printInvalidTaskIndexError();
        }
    }

    private static void unmarkTask(String input, ArrayList<Task> tasks) {
        try {
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.get(index).unMarkAsDone();
                System.out.println("____________________________________________________________\n"
                        + "OK, I've marked this task as not done yet:\n"
                        + tasks.get(index).toString() + "\n"
                        + "____________________________________________________________");
            } else {
                printInvalidTaskIndexError();
            }
        } catch (NumberFormatException e) {
            printInvalidTaskIndexError();
        }
    }

    private static void handleTaskCommand(ArrayList<Task> tasks, String type, String input, int prefixLength) {
        if (input.length() <= prefixLength) {
            printEmptyDescriptionError(type);
            return;
        }

        String description = input.substring(prefixLength).trim();
        if (description.isEmpty()) {
            printEmptyDescriptionError(type);
            return;
        }

        Task newTask;
        switch (type) {
            case "todo":
                newTask = new ToDo(description);
                break;
            case "deadline":
                String[] deadlineParts = description.split("/by ");
                if (deadlineParts.length == 2) {
                    newTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                } else {
                    printEmptyDescriptionError(type);
                    return;
                }
                break;
            case "event":
                String[] eventParts = description.split("/from ");
                if (eventParts.length == 2) {
                    String[] eventDetails = eventParts[1].split("/to ");
                    if (eventDetails.length == 2) {
                        newTask = new Event(eventParts[0].trim(), eventDetails[0].trim(), eventDetails[1].trim());
                    } else {
                        printEmptyDescriptionError(type);
                        return;
                    }
                } else {
                    printEmptyDescriptionError(type);
                    return;
                }
                break;
            default:
                printUnknownCommandError();
                return;
        }

        tasks.add(newTask);
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + newTask.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n"
                + "____________________________________________________________");
    }

    private static void printInvalidTaskIndexError() {
        System.out.println("____________________________________________________________\n"
                + "Oh no!!! The task index is invalid.\n"
                + "____________________________________________________________");
    }

    private static void printEmptyDescriptionError(String type) {
        System.out.println("____________________________________________________________\n"
                + "Oh no!!! The description of a " + type + " cannot be empty.\n"
                + "____________________________________________________________");
    }

    private static void printUnknownCommandError() {
        System.out.println("____________________________________________________________\n"
                + "Oh no!!! I'm sorry, but I don't understand that command.\n"
                + "____________________________________________________________");
    }
}
