package optimus;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.IOException;

public class Parser {

    public static String parseCommand(String text, TaskList record, Ui ui, Storage storage) throws OptimusException, IOException {
        assert text != null : "Input text should not be null";
        text = text.trim();
        assert !text.isEmpty() : "Input text should not be empty";
        if (text.equals("bye")) {
            return handleByeCommand(record, ui, storage);
        } else if (text.startsWith("find")) {
            return handleFindCommand(text, record, ui);
        } else if (text.equals("list")) {
            return handleListCommand(record, ui);
        } else if (text.startsWith("delete")) {
            return handleDeleteCommand(text, record, ui);
        } else if (text.startsWith("mark")) {
            return handleMarkCommand(text, record, ui);
        } else if (text.startsWith("unmark")) {
            return handleUnmarkCommand(text, record, ui);
        } else if (text.startsWith("todo")) {
            return handleTodoCommand(text, record, ui);
        } else if (text.startsWith("deadline")) {
            return handleDeadlineCommand(text, record, ui);
        } else if (text.startsWith("event")) {
            return handleEventCommand(text, record, ui);
        } else {
            throw new OptimusException("I don't understand that command. Please try again with a valid command.");
        }
    }

    private static String handleByeCommand(TaskList record, Ui ui, Storage storage) throws IOException {
        storage.saveToFile(record.getTasks());
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
        return ui.showGoodbye();
    }

    private static String handleFindCommand(String text, TaskList record, Ui ui) throws OptimusException {
        if (text.trim().length() <= 4 || text.substring(4).trim().isEmpty()) {
            throw new OptimusException("The keyword provided is empty. Please provide a valid search keyword.");
        }
        String keyword = text.substring(5).trim();
        return record.findTasks(keyword, ui);
    }

    private static String handleListCommand(TaskList record, Ui ui) {
        return ui.listTasks(record);
    }

    private static String handleDeleteCommand(String text, TaskList record, Ui ui) throws OptimusException {
        String[] parts = text.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new OptimusException("The task number provided is empty. Please provide a valid task number.");
        }
        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= record.sizeOfRecord()) {
            throw new OptimusException("The task number provided is out of range. Please provide a valid task number.");
        }
        Task taskToDelete = record.getTask(taskNumber);
        record.deleteTask(taskNumber);
        return ui.TaskDeleted(taskToDelete, record.sizeOfRecord());
    }

    private static String handleMarkCommand(String text, TaskList record, Ui ui) throws OptimusException {
        String[] parts = text.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new OptimusException("The task number provided is empty. Please provide a valid task number.");
        }
        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= record.sizeOfRecord()) {
            throw new OptimusException("The task number provided is out of range. Please provide a valid task number.");
        }
        Task taskToMark = record.getTask(taskNumber);
        taskToMark.setDone();
        return ui.TaskMarked(taskToMark);
    }

    private static String handleUnmarkCommand(String text, TaskList record, Ui ui) throws OptimusException {
        String[] parts = text.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new OptimusException("The task number provided is empty. Please provide a valid task number.");
        }
        int taskNumber = Integer.parseInt(parts[1]) - 1;
        if (taskNumber < 0 || taskNumber >= record.sizeOfRecord()) {
            throw new OptimusException("The task number provided is out of range. Please provide a valid task number.");
        }
        Task taskToUnmark = record.getTask(taskNumber);
        taskToUnmark.setNotDone();
        return ui.TaskUnmarked(taskToUnmark);
    }

    private static String handleTodoCommand(String text, TaskList record, Ui ui) throws OptimusException {
        if (text.trim().length() <= 4 || text.substring(4).trim().isEmpty()) {
            throw new OptimusException("The description of a todo cannot be empty. Please provide a task description.");
        }
        String description = text.substring(5).trim();
        Task newTask = new ToDos(description);
        record.addTask(newTask);
        return ui.taskAdded(newTask, record.sizeOfRecord());
    }

    private static String handleDeadlineCommand(String text, TaskList record, Ui ui) throws OptimusException {
        String[] parts = text.split(" /by ");
        if (parts.length < 2) {
            throw new OptimusException("The deadline format is incorrect. Please provide a description and a deadline " +
                    "(eg. deadline desc /by date-time).");
        }
        String description = parts[0].substring(9).trim();
        String by = parts[1].trim();
        Task newTask = new Deadlines(description, by);
        record.addTask(newTask);
        return ui.taskAdded(newTask, record.sizeOfRecord());
    }

    private static String handleEventCommand(String text, TaskList record, Ui ui) throws OptimusException {
        String[] parts = text.split(" /from | /to ");
        if (parts.length < 3) {
            throw new OptimusException("The event time format is incorrect. Please provide both 'from' and 'to' times with" +
                    "the description (eg. event desc /from from /to to).");
        }
        String description = parts[0].substring(6).trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Task newTask = new Events(description, from, to);
        record.addTask(newTask);
        return ui.taskAdded(newTask, record.sizeOfRecord());
    }
}
