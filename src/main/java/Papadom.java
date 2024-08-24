import Exceptions.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage("src/main/java/Storage/tasks.txt");
    private static final Parser parser = new Parser();
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    /**
     * Outputs a formatted message with a surrounding border to the console.
     *
     * @param message The message to be printed to the console.
     */

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
        storage.addTaskToDatabase(task);
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
    private static String addDeadline(String details) throws NoTaskException, NoDateException, IncorrectDeadlineDateFormatException {
        Deadline newDeadlineTask = parser.deadlineTaskCreator(details);
        return addToList(newDeadlineTask);
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
        Event newEventTask = parser.eventTaskCreator(details);
        return addToList(newEventTask);
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
    private static void createFileIfNotPresent() {
        String filePath = "src/main/java/Storage/tasks.txt";
        File file = new File(filePath);
        try {
            // Check if the file exists
            if (!file.exists()) {
                // If the file doesn't exist, create it along with any necessary directories
                file.getParentFile().mkdirs(); // Create directories if they don't exist
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        ui.welcomeMessage();
        createFileIfNotPresent();

        while (true) {
            try {
                String text = scanner.nextLine();
                String commandText = text.split(" ")[0];
                Command command = Command.fromString(commandText);
                switch (command) {
                case LIST:
                    ui.output(printList());
                    break;
                case BYE:
                    ui.exitMessage();
                    return;
                case MARK:
                    ui.output(markTask(text));
                    break;
                case UNMARK:
                    ui.output(unmarkTask(text));
                    break;
                case TODO:
                    Todo todoTask = new Todo(text.substring(5));
                    ui.output(addToList(todoTask));
                    break;
                case DEADLINE:
                    ui.output(addDeadline(text.substring(9)));
                    break;
                case EVENT:
                    ui.output(addEvent(text.substring(6)));
                    break;
                case DELETE:
                    ui.output(deleteEvent(text));
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (Exception e) {
                ui.output(e.getMessage());
            }
        }
    }
}
