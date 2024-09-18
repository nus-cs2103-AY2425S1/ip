package meowmeow;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser that processes the user commands and handles task management.
 */
public class Parser {
    private TaskList tasks;
    private Storage saver;

    /**
     * Initializes the Parser with the task list and storage.
     *
     * @param list  The task list to operate on.
     * @param saver The storage handler to save data.
     */
    public Parser(TaskList list, Storage saver) {
        this.tasks = list;
        this.saver = saver;
    }

    /**
     * Processes the input command and performs the appropriate action.
     *
     * @param initInput The user's input command.
     * @return The appropriate response based on the input.
     * @throws IOException If an I/O error occurs during saving.
     */
    public String parse(String initInput) throws IOException {
        switch (getCommand(initInput)) {
        case "list":
            return handleList();
        case "find":
            return handleFind(initInput);
        case "mark":
            return handleMark(initInput);
        case "unmark":
            return handleUnmark(initInput);
        case "todo":
            return handleTodo(initInput);
        case "deadline":
            return handleDeadline(initInput);
        case "event":
            return handleEvent(initInput);
        case "delete":
            return handleDelete(initInput);
        case "bye":
            return Ui.getGoodbyeMessage();
        default:
            return Ui.getUnknownCommandMessage();
        }
    }

    /**
     * Extracts the command type from the user's input.
     *
     * @param input The user's input.
     * @return The command as a string.
     */
    private String getCommand(String input) {
        return input.split(" ")[0];
    }

    private String handleList() {
        return Ui.getTaskListMessage(tasks);
    }

    private String handleFind(String input) {
        String keyword = input.substring(5);
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return Ui.getFindTasksMessage(matchingTasks);
    }

    private String handleMark(String input) throws IOException {
        int taskNumber = Integer.parseInt(input.substring(5)) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).markDone();
            saver.saveData();
            return Ui.getMarkTaskDoneMessage(tasks.get(taskNumber));
        } else {
            return Ui.getInvalidTaskNumberMessage();
        }
    }

    private String handleUnmark(String input) throws IOException {
        int taskNumber = Integer.parseInt(input.substring(7)) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            tasks.get(taskNumber).unMark();
            saver.saveData();
            return Ui.getUnmarkTaskMessage(tasks.get(taskNumber));
        } else {
            return Ui.getInvalidTaskNumberMessage();
        }
    }

    private String handleTodo(String input) throws IOException {
        String description = input.substring(5);
        ToDo todo = new ToDo(description);
        tasks.add(todo);
        saver.saveData();
        return Ui.getAddTodoMessage(todo, tasks.size());
    }

    /**
     * Validates if the date string matches the expected format (yyyy-MM-dd).
     *
     * @param date The date string to validate.
     * @return True if the date format is valid, false otherwise.
     */
    private boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private String handleDeadline(String input) throws IOException {
        String[] parts = input.substring(9).split(" /by ");
        boolean isWrongPartLength = parts.length <= 1;
        if (isWrongPartLength || !(isValidDate(parts[1]))) {
            return Ui.getInvalidDeadlineMessage();
        } else {
            String description = parts[0];
            String by = parts[1];
            Deadline deadline = new Deadline(description, by);
            tasks.add(deadline);
            saver.saveData();
            return Ui.getAddDeadlineMessage(deadline, tasks.size());
        }
    }

    private String handleEvent(String input) throws IOException {
        String[] parts = input.substring(6).split(" /from | /to ");
        boolean isWrongPartsLength = parts.length <= 1;
        if (isWrongPartsLength || !(isValidDate(parts[1])) || !(isValidDate(parts[2]))) {
            return Ui.getInvalidEventMessage();
        } else {
            String description = parts[0];
            String from = parts[1];
            String to = parts[2];
            Event event = new Event(description, from, to);
            tasks.add(event);
            saver.saveData();
            return Ui.getAddEventMessage(event, tasks.size());
        }
    }

    private String handleDelete(String input) throws IOException {
        int taskNumber = Integer.parseInt(input.substring(7)) - 1;
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            Task removedTask = tasks.remove(taskNumber);
            saver.saveData();
            return Ui.getDeleteTaskMessage(removedTask, tasks.size());
        } else {
            return Ui.getInvalidTaskNumberMessage();
        }
    }
}

