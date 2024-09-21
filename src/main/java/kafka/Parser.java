package kafka;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * This class parses user input commands and executes corresponding actions within the Kafka application.
 * It interacts with TaskList, Storage, and Ui objects to manage tasks, persist data, and provide user feedback.
 */

public class Parser {

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
        String[] splitInput = userInput.trim().split(" ", 2);

        if (splitInput[0] == null) {
            return "";
        }

        String command = splitInput[0].toLowerCase();
        String arguments = splitInput.length > 1 ? splitInput[1] : "";
        String output = "";

        try {
            output = switch (command) {
                case "bye" -> executeByeCommand(ui);
                case "list" -> executeListCommand(taskList, ui);
                case "mark" -> executeMarkCommand(arguments, taskList, storage, ui);
                case "unmark" -> executeUnmarkCommand(arguments, taskList, storage, ui);
                case "delete" -> executeDeleteCommand(arguments, taskList, storage, ui);
                case "find" -> executeFindCommand(arguments, taskList, ui);
                case "todo" -> executeTodoCommand(arguments, taskList, storage, ui);
                case "deadline" -> executeDeadlineCommand(arguments, taskList, storage, ui);
                case "event" -> executeEventCommand(arguments, taskList, storage, ui);
                default ->
                        throw new KafkaException("Hmm... I'm not sure what you're getting at. Care to enlighten me?");
            };
        } catch (IOException e) {
            ui.showError(e);
        } catch (DateTimeParseException e) {
            ui.incorrectDateDetails();
        }

        return output;
    }

    private static String executeByeCommand(Ui ui) {
        return ui.goodbye();
    }

    private static String executeListCommand(TaskList taskList, Ui ui) {
        ui.getList();
        return taskList.printList();
    }

    private static String executeMarkCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException {
        int taskNumberMark = Integer.parseInt(arguments);
        Task taskToMark = taskList.tasks.get(taskNumberMark - 1);
        taskList.mark(taskToMark);
        storage.writeToFile(taskList.tasks);
        return ui.mark(taskToMark);
    }

    private static String executeUnmarkCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException {
        int taskNumberUnmark = Integer.parseInt(arguments);
        Task taskToUnmark = taskList.tasks.get(taskNumberUnmark - 1);
        taskList.unmark(taskToUnmark);
        storage.writeToFile(taskList.tasks);
        return ui.unmark(taskToUnmark);
    }

    private static String executeDeleteCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException {
        if (taskList.tasks.isEmpty()) {
            return "";
        }
        int taskNumberDelete = Integer.parseInt(arguments);
        Task taskToDelete = taskList.tasks.get(taskNumberDelete - 1);
        taskList.delete(taskNumberDelete);
        storage.writeToFile(taskList.tasks);
        return ui.delete(taskToDelete, taskList);
    }

    private static String executeFindCommand(String arguments, TaskList taskList, Ui ui) throws KafkaException {
        if (arguments.isEmpty()) {
            throw new KafkaException("It seems you've left the details blank. Even the simplest tasks need some direction, don't you think?");
        }
        TaskList temp = taskList.find(arguments.toLowerCase());
        if (temp.isEmpty()) {
            throw new KafkaException("Hmm, it seems that no task aligns with that word... mind trying again?");
        }
        ui.find();
        return temp.printList();
    }

    private static String executeTodoCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException, KafkaException {
        if (arguments.isEmpty()) {
            throw new KafkaException("It seems you've left the details blank. Even the simplest tasks need some direction, don't you think?");
        }
        Task todo = new Todo(arguments, false);
        taskList.addTask(todo);
        storage.writeToFile(taskList.tasks);
        return ui.addTask(todo, taskList);
    }

    private static String executeDeadlineCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException, KafkaException {
        if (arguments.isEmpty()) {
            throw new KafkaException("It seems you've left the details blank. Even the simplest tasks need some direction, don't you think?");
        }
        String[] deadlineParts = arguments.split("/by ");
        if (deadlineParts.length < 2) {
            throw new KafkaException("It appears the details for this deadline task are off. Let's give it another go, shall we?");
        }
        LocalDateTime by = LocalDateTimeConverter.getLocalDateTime(deadlineParts[1]);
        Task deadline = new Deadline(deadlineParts[0], by, false);
        taskList.addTask(deadline);
        storage.writeToFile(taskList.tasks);
        return ui.addTask(deadline, taskList);
    }

    private static String executeEventCommand(String arguments, TaskList taskList, Storage storage, Ui ui) throws IOException, KafkaException {
        if (arguments.isEmpty()) {
            throw new KafkaException("It seems you've left the details blank. Even the simplest tasks need some direction, don't you think?");
        }
        String[] eventParts = arguments.split("/from | /to ");
        if (eventParts.length < 3) {
            throw new KafkaException("It appears the details for this event task are off. Let's give it another go, shall we?");
        }
        LocalDateTime from = LocalDateTimeConverter.getLocalDateTime(eventParts[1]);
        LocalDateTime to = LocalDateTimeConverter.getLocalDateTime(eventParts[2]);
        Task event = new Event(eventParts[0], from, to, false);
        taskList.addTask(event);
        storage.writeToFile(taskList.tasks);
        return ui.addTask(event, taskList);
    }
}