package carter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Handles the parsing of the user command for Carter.
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param input The user input command as String.
     * @param tasks The TaskList containing the current tasks.
     * @param res The response used to display message to the user.
     * @return return String representation of response.
     * @throws CarterException If the user input is invalid or cannot be recognized.
     */
    public static String parse(String input, TaskList tasks, Response res) throws CarterException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
        case "list":
            return handleListCommand(tasks, res);
        case "mark":
            return handleMarkCommand(words, tasks, res);
        case "unmark":
            return handleUnmarkCommand(words, tasks, res);
        case "todo":
            return handleTodoCommand(words, tasks, res);
        case "deadline":
            return handleDeadlineCommand(words, tasks, res);
        case "event":
            return handleEventCommand(words, tasks, res);
        case "delete":
            return handleDeleteCommand(words, tasks, res);
        case "find":
            return handleFindCommand(words, tasks, res);
        default:
            throw new CarterException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseTaskIndex(String indexStr, int taskLength) throws CarterException {
        try {
            int index = Integer.parseInt(indexStr) - 1;
            if (index < 0 || index >= taskLength) {
                throw new CarterException("Please provide a valid number");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new CarterException("Please provide a valid number");
        }
    }

    private static String handleListCommand(TaskList tasks, Response res) {
        Task[] output = new Task[tasks.getLength()];
        return res.showList(tasks.getTasks().toArray(output));
    }

    private static String handleMarkCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        if (words.length < 2) {
            throw new CarterException("Please provide the task number to mark.");
        }
        int markIndex = parseTaskIndex(words[1], tasks.getLength());
        Task currMarkTask = tasks.markTask(markIndex);
        return res.showMarkTaskMessage(currMarkTask);
    }

    private static String handleUnmarkCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        if (words.length < 2) {
            throw new CarterException("Please provide the task number to unmark.");
        }
        int unmarkIndex = parseTaskIndex(words[1], tasks.getLength());
        Task currUnmarkTask = tasks.unMarkTask(unmarkIndex);
        return res.showUnmarkTaskMessage(currUnmarkTask);
    }

    private static String handleTodoCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new CarterException("The description of a todo cannot be empty.");
        }
        ToDo toDoTask = new ToDo(words[1]);
        tasks.addTask(toDoTask);
        return res.showTask(toDoTask, tasks.getLength());
    }

    private static String handleDeadlineCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        String[] deadlineDetail = words[1].split("/", 2);
        if (deadlineDetail.length < 2 || deadlineDetail[0].trim().isEmpty() || deadlineDetail[1].trim().isEmpty()) {
            throw new CarterException("The description and deadline of a deadline task cannot be empty.");
        }
        try {
            Deadline deadlineTask = new Deadline(deadlineDetail[0],
                    LocalDateTime.parse(deadlineDetail[1].substring(3), FORMATTER));
            tasks.addTask(deadlineTask);
            return res.showTask(deadlineTask, tasks.getLength());
        } catch (DateTimeParseException e) {
            throw new CarterException("Please fill in the detail with a valid format.");
        }
    }

    private static String handleEventCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        String[] eventDetail = words[1].split("/");
        if (eventDetail.length < 3 || eventDetail[0].trim().isEmpty()
                || eventDetail[1].trim().isEmpty() || eventDetail[2].trim().isEmpty()) {
            throw new CarterException("The description and times of an event task cannot be empty.");
        }
        try {
            Event eventTask = new Event(eventDetail[0],
                    LocalDateTime.parse(eventDetail[1].substring(5).trim(), FORMATTER),
                    LocalDateTime.parse(eventDetail[2].substring(3).trim(), FORMATTER));
            tasks.addTask(eventTask);
            return res.showTask(eventTask, tasks.getLength());
        } catch (DateTimeParseException e) {
            throw new CarterException("Please fill in the detail with a valid format.");
        }
    }

    private static String handleDeleteCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        if (words.length < 2) {
            throw new CarterException("Please provide the task number to delete.");
        }
        int deleteTaskNumber = parseTaskIndex(words[1], tasks.getLength());
        Task deletedTask = tasks.deleteTask(deleteTaskNumber);
        return res.showTaskDeleted(deletedTask, tasks.getLength());
    }

    private static String handleFindCommand(String[] words, TaskList tasks, Response res) throws CarterException {
        String keyword = words.length > 1 ? words[1].trim() : "";
        if (keyword.isEmpty()) {
            throw new CarterException("Please provide a keyword to search for.");
        }
        List<Task> taskList = tasks.find(keyword);
        return res.showMatchingTask(taskList.toArray(new Task[0]));
    }

}
