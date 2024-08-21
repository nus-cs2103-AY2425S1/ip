import Exceptions.*;

import java.util.ArrayList;
import java.util.Scanner;
public class Papadom {
    enum Command {
        LIST, BYE, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN;
        public static Command fromString(String command) {
            return switch (command.toLowerCase()) {
                case "list" -> LIST;
                case "bye" -> BYE;
                case "mark" -> MARK;
                case "unmark" -> UNMARK;
                case "todo" -> TODO;
                case "deadline" -> DEADLINE;
                case "event" -> EVENT;
                case "delete" -> DELETE;
                default -> UNKNOWN;
            };
        }
    }
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Outputs a formatted message with a surrounding border to the console.
     *
     * @param message The message to be printed to the console.
     */
    public static void output(String message) {
        System.out.println("____________________________________________________________\n"
                + message
                + "\n____________________________________________________________");
    }

    /**
     * Returns a formatted string of all tasks in the list.
     *
     * @return A formatted string containing the list of tasks.
     */
    public static String printList() {
        String finalList = " Here are the tasks in your list:";
        for (int i = 0; i < Papadom.tasks.size(); i++) {
            Task task = Papadom.tasks.get(i);
            if (task == null) {
                break;
            }
            finalList += "\n " + (i + 1) + "." + task.toString();
        }
        return finalList;
    }

    /**
     * Adds a task to the list and returns a confirmation message.
     *
     * @param task The task to be added to the list.
     * @return A confirmation message indicating the task has been added.
     * @throws NoTaskException If the task description is empty.
     */
    public static String addToList(Task task) throws NoTaskException {
        if (task.description == "") {
            throw new NoTaskException();
        }
        tasks.add(task);
        String response = " Got it. I've added this task:\n  " + task.toString() + "\n"
                + " Now you have " + (Papadom.tasks.size()) + " tasks in the list.";
        return response;
    }

    /**
     * Marks a task as done based on the given input string.
     *
     * @param text The input string containing the task number to be marked.
     * @return A message confirming the task has been marked as done.
     * @throws NoTaskNumberException If the task number is missing or not a number.
     * @throws WrongTaskNumberException If the task number is out of range.
     */
    public static String markTask(String text) throws NoTaskNumberException, WrongTaskNumberException {
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= Papadom.tasks.size()) {
                throw new WrongTaskNumberException();
            }
            Task task = Papadom.tasks.get(taskIndex);
            task.markAsDone();
            return " Nice! I've marked this task as done:\n  " + task;
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException(); // Throw the custom exception if parts[1] is not an integer
        }
    }

    /**
     * Unmarks a task as not done based on the given input string.
     *
     * @param text The input string containing the task number to be unmarked.
     * @return A message confirming the task has been unmarked as not done.
     * @throws NoTaskNumberException If the task number is missing or not a number.
     * @throws WrongTaskNumberException If the task number is out of range.
     */
    private static String unmarkTask(String text) throws NoTaskNumberException, WrongTaskNumberException {
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= Papadom.tasks.size()) {
                throw new WrongTaskNumberException();
            }
            Task task = Papadom.tasks.get(taskIndex);
            task.unmark();
            return " OK, I've marked this task as not done yet:\n  " + task;
            // Proceed with your logic using taskIndex
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException(); // Throw the custom exception if parts[1] is not an integer
        }
    }

    /**
     * Adds a deadline task to the list based on the given details.
     *
     * @param details The details of the task, including the description and deadline.
     * @return A confirmation message indicating the deadline has been added.
     * @throws NoTaskException If the task description is empty.
     * @throws NoDateException If the deadline date is missing.
     */
    private static String addDeadline(String details) throws NoTaskException, NoDateException {
        String[] parts = details.split(" /by ");
        if (parts[0] == "") {
            throw new NoTaskException();
        } else if (parts.length == 1) {
            throw new NoDateException();
        }
        return addToList(new Deadline(parts[0], parts[1]));
    }

    /**
     * Adds an event task to the list based on the given details.
     *
     * @param details The details of the task, including the description, start time, and end time.
     * @return A confirmation message indicating the event has been added.
     * @throws NoTaskException If the task description is empty.
     * @throws NoDateException If the event dates are missing.
     */
    private static String addEvent(String details) throws NoTaskException, NoDateException {
        String[] parts = details.split(" /from | /to ");
        if (parts[0] == "") {
            throw new NoTaskException();
        } else if (parts.length <= 2) {
            throw new NoDateException();
        }
        return addToList(new Event(parts[0], parts[1], parts[2]));
    }

    /**
     * Deletes a task from the list based on the given input string.
     *
     * @param text The input string containing the task number to be deleted.
     * @return A message confirming the task has been deleted.
     * @throws NoTaskNumberException If the task number is missing or not a number.
     * @throws WrongTaskNumberException If the task number is out of range.
     */
    private static String deleteEvent(String text) throws NoTaskNumberException, WrongTaskNumberException {
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= Papadom.tasks.size()) {
                throw new WrongTaskNumberException();
            }
            Task taskToBeDeleted = Papadom.tasks.get(taskIndex);
            Papadom.tasks.remove(taskIndex);
            return " Noted. I've removed this task:\n  " + taskToBeDeleted + "\n Now you have " + Papadom.tasks.size() + " tasks in the list.";
            // Proceed with your logic using taskIndex
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException(); // Throw the custom exception if parts[1] is not an integer
        }
    }
    public static void main(String[] args) {
        Papadom.output(" Hello! I'm Papadom\n"
                + " What can I do for you?");

        while (true) {
            try {
                String text = scanner.nextLine();
                String commandText = text.split(" ")[0];
                Command command = Command.fromString(commandText);
                switch (command) {
                case LIST:
                    output(printList());
                    break;
                case BYE:
                    output(" Bye. Hope to see you again soon!");
                    return;
                case MARK:
                    output(markTask(text));
                    break;
                case UNMARK:
                    output(unmarkTask(text));
                    break;
                case TODO:
                    output(addToList(new Todo(text.substring(5))));
                    break;
                case DEADLINE:
                    output(addDeadline(text.substring(9)));
                    break;
                case EVENT:
                    output(addEvent(text.substring(6)));
                    break;
                case DELETE:
                    output(deleteEvent(text));
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (Exception e) {
                output(e.getMessage());
            }
        }
    }
}
