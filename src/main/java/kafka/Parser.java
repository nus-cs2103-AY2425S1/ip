package kafka;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * This class parses user input commands and executes corresponding actions within the Kafka application.
 * It interacts with TaskList, Storage, and Ui objects to manage tasks, persist data, and provide user feedback.
 */

public class Parser {

    private static final String BY_DELIMITER = " /by ";
    private static final String FROM_TO_DELIMITER = " /from | /to ";

    /**
     * Parses the given user input and executes the appropriate command.
     *
     * @param userInput The user input string.
     * @param taskList The TaskList object to manage tasks.
     * @param storage The Storage object for data persistence.
     * @param ui The Ui object for user interface interactions.
     * @throws KafkaException If an error occurs during command parsing or execution.
     * @throws IOException If an error occurs during file operations.
     * @throws DateTimeParseException If an error occurs while parsing date and time strings.
     */
    public static String parseCommand(String userInput, TaskList taskList, Storage storage, Ui ui) throws KafkaException, IOException, DateTimeParseException {
        assert userInput != null && !userInput.trim().isEmpty() : "User input cannot be null or empty";
        String[] splitInput = userInput.trim().split(" ", 2);

        if (splitInput[0] == null) {
            return "";
        }

        String command = splitInput[0].toLowerCase();
        String arguments = splitInput.length > 1 ? splitInput[1] : "";
        String output;

        try {
            output = switch (command) {
                case "bye" -> executeByeCommand(ui);
                case "list" -> executeListCommand(taskList, ui);
                case "mark" -> executeMarkCommand(arguments, taskList, storage, ui);
                case "unmark" -> executeUnmarkCommand(arguments, taskList, storage, ui);
                case "snooze" -> executeSnoozeCommand(arguments, taskList, storage, ui);
                case "delete" -> executeDeleteCommand(arguments, taskList, storage, ui);
                case "find" -> executeFindCommand(arguments, taskList, ui);
                case "todo" -> executeTodoCommand(arguments, taskList, storage, ui);
                case "deadline" -> executeDeadlineCommand(arguments, taskList, storage, ui);
                case "event" -> executeEventCommand(arguments, taskList, storage, ui);
                default ->
                        throw new KafkaException("Hmm... I'm not sure what you're getting at. Care to enlighten me?");
            };
        } catch (IOException e) {
            output = ui.showError(e);
        } catch (DateTimeParseException e) {
            output = ui.incorrectDateDetails();
        }
        return output;
    }

    /**
     * Executes the "bye" command, which triggers the application to exit.
     *
     * @param ui The user interface object.
     * @return A message indicating the application's exit.
     */
    private static String executeByeCommand(Ui ui) {
        return ui.goodbye();
    }

    /**
     * Executes the "list" command, which displays all tasks in the task list.
     *
     * @param taskList The task list object.
     * @param ui The user interface object.
     * @return A string containing the list header and the formatted task list.
     */
    private static String executeListCommand(TaskList taskList, Ui ui) {
        return ui.getList() + "\n" + taskList.printList();
    }

    /**
     * Executes the "mark" command, which marks a specified task as completed.
     *
     * @param arguments The task number to mark.
     * @param taskList The task list object.
     * @param storage The storage object for saving task data.
     * @param ui The user interface object.
     * @return A message indicating the task has been marked as completed.
     * @throws IOException If there's an error writing to the storage.
     */
    private static String executeMarkCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException, KafkaException {
        checkForEmptyArguments(arguments);
        int taskNumberMark = Integer.parseInt(arguments);
        Task taskToMark = taskList.tasks.get(taskNumberMark - 1);
        taskList.mark(taskToMark);
        storage.writeToFile(taskList.tasks);
        return ui.mark(taskToMark);
    }

    /**
     * Executes the "unmark" command, which marks a specified task as incomplete.
     *
     * @param arguments The task number to unmark.
     * @param taskList The task list object.
     * @param storage The storage object for saving task data.
     * @param ui The user interface object.
     * @return A message indicating the task has been marked as incomplete.
     * @throws IOException If there's an error writing to the storage.
     */
    private static String executeUnmarkCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException, KafkaException {
        checkForEmptyArguments(arguments);
        int taskNumberUnmark = Integer.parseInt(arguments);
        Task taskToUnmark = taskList.tasks.get(taskNumberUnmark - 1);
        taskList.unmark(taskToUnmark);
        storage.writeToFile(taskList.tasks);
        return ui.unmark(taskToUnmark);
    }

    /**
     * Executes the "snooze" command, which reschedules a specified task to a later time.
     *
     * @param arguments The task number and new time for the task (separated by "by" or "from" and "to").
     * @param taskList The task list object.
     * @param storage The storage object for saving task data.
     * @param ui The user interface object.
     * @return A message indicating the task has been snoozed.
     * @throws IOException If there's an error writing to the storage.
     * @throws KafkaException If there's an error in the snoozing process.
     */
    private static String executeSnoozeCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException, KafkaException {
        checkForEmptyArguments(arguments);
        if (arguments.contains("/by")) {
            String[] taskParts = arguments.split(BY_DELIMITER);
            int taskNumber = Integer.parseInt(taskParts[0]);
            Task taskToSnooze = taskList.tasks.get(taskNumber - 1);
            LocalDateTime by = LocalDateTimeConverter.getLocalDateTime(taskParts[1]);
            if (!(taskToSnooze instanceof Deadline deadline)) {
                throw new KafkaException("  Looks like this is not a deadline task, mind using /from and /to?");
            }
            deadline.snooze(by);
            storage.writeToFile(taskList.tasks);
            return ui.snooze(taskToSnooze);
        } else if (arguments.contains("/from")) {
            String[] taskParts = arguments.split(FROM_TO_DELIMITER);
            int taskNumber = Integer.parseInt(taskParts[0]);
            Task taskToSnooze = taskList.tasks.get(taskNumber - 1);
            LocalDateTime from = LocalDateTimeConverter.getLocalDateTime(taskParts[1]);
            if (!(taskToSnooze instanceof Event event)) {
                throw new KafkaException("  Looks like this is not a event task, mind using /by?");
            }
            if (taskParts.length < 3) {
                event.snooze(from);
            } else {
                LocalDateTime to = LocalDateTimeConverter.getLocalDateTime(taskParts[2]);
                event.snooze(from, to);
            }
            storage.writeToFile(taskList.tasks);
            return ui.snooze(taskToSnooze);
        } else {
            throw new KafkaException("  Looks like this is not a task that can be snoozed, mind trying other tasks?");
        }
    }

    /**
     * Executes the "delete" command, which deletes a specified task from the task list.
     *
     * @param arguments The task number to delete.
     * @param taskList The task list object.
     * @param storage The storage object for saving task data.
     * @param ui The user interface object.
     * @return A message indicating the task has been deleted.
     * @throws IOException If there's an error writing to the storage.
     */
    private static String executeDeleteCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException, KafkaException {
        checkForEmptyArguments(arguments);
        if (taskList.tasks.isEmpty()) {
            return "";
        }
        int taskNumberDelete = Integer.parseInt(arguments);
        Task taskToDelete = taskList.tasks.get(taskNumberDelete - 1);
        taskList.delete(taskNumberDelete);
        storage.writeToFile(taskList.tasks);
        return ui.delete(taskToDelete, taskList);
    }

    /**
     * Executes the "find" command, which searches for tasks containing a specified keyword.
     *
     * @param arguments The keyword to search for.
     * @param taskList The task list object.
     * @param ui The user interface object.
     * @return A string containing the search results.
     * @throws KafkaException If there's an error during the search.
     */
    private static String executeFindCommand(String arguments, TaskList taskList, Ui ui) throws KafkaException {
        checkForEmptyArguments(arguments);
        TaskList temp = taskList.find(arguments.toLowerCase());
        if (temp.isEmpty()) {
            throw new KafkaException("Hmm, it seems that no task aligns with that word... mind trying again?");
        }
        return ui.find() + "\n" + temp.printList();
    }

    /**
     * Executes the "todo" command, which creates a new todo task.
     *
     * @param arguments The description of the todo task.
     * @param taskList The task list object.
     * @param storage The storage object for saving task data.
     * @param ui The user interface object.
     * @return A message indicating the task has been added.
     * @throws IOException If there's an error writing to the storage.
     * @throws KafkaException If there's an error in the task creation process.
     */
    private static String executeTodoCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException, KafkaException {
        checkForEmptyArguments(arguments);
        Task todo = new Todo(arguments, false);
        taskList.addTask(todo);
        storage.writeToFile(taskList.tasks);
        return ui.addTask(todo, taskList);
    }

    /**
     * Executes the "deadline" command, which creates a new deadline task with a specified deadline.
     *
     * @param arguments The description and deadline of the task (separated by "by").
     * @param taskList The task list object.
     * @param storage The storage object for saving task data.
     * @param ui The user interface object.
     * @return A message indicating the task has been added.
     * @throws IOException If there's an error writing to the storage.
     * @throws KafkaException If there's an error in the task creation process.
     */
    private static String executeDeadlineCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException, KafkaException {
        checkForEmptyArguments(arguments);
        String[] deadlineParts = arguments.split(BY_DELIMITER);
        if (deadlineParts.length != 2 || deadlineParts[0] == null) {
            throw new KafkaException("It appears the details for this deadline task are off. Let's try adding descriptions, shall we?");
        }
        LocalDateTime by = LocalDateTimeConverter.getLocalDateTime(deadlineParts[1]);
        Task deadline = new Deadline(deadlineParts[0], by, false);
        taskList.addTask(deadline);
        storage.writeToFile(taskList.tasks);
        return ui.addTask(deadline, taskList);
    }

    /**
     * Executes the "event" command, which creates a new event task with specified start and end times.
     *
     * @param arguments The description, start time, and end time of the event (separated by "from" and "to").
     * @param taskList The task list object.
     * @param storage The storage object for saving task data.
     * @param ui The user interface object.
     * @return A message indicating the task has been added.
     * @throws IOException If there's an error writing to the storage.
     * @throws KafkaException If there's an error in the task creation process.
     */
    private static String executeEventCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException, KafkaException {
        checkForEmptyArguments(arguments);
        String[] eventParts = arguments.split(FROM_TO_DELIMITER);
        if (eventParts.length != 3 || eventParts[0] == null) {
            throw new KafkaException("It appears the details for this event task are off. Let's give it another go, shall we?");
        }
        LocalDateTime from = LocalDateTimeConverter.getLocalDateTime(eventParts[1]);
        LocalDateTime to = LocalDateTimeConverter.getLocalDateTime(eventParts[2]);
        Task event = new Event(eventParts[0], from, to, false);
        taskList.addTask(event);
        storage.writeToFile(taskList.tasks);
        return ui.addTask(event, taskList);
    }

    /**
     * Checks if the given arguments are empty.
     *
     * @param arguments The arguments to check.
     * @throws KafkaException If the arguments are empty.
     */
    private static void checkForEmptyArguments(String arguments) throws KafkaException {
        if (arguments.isEmpty()) {
            throw new KafkaException("It seems you've left the details blank. Even the simplest tasks need some direction, don't you think?");
        }
    }
}