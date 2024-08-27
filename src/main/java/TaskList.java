import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int index) throws AstaException {
        if (index < 0 || index >= tasks.size()) {
            throw new AstaException("Invalid task number.");
        }
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public int getSize() {
        return tasks.size();
    }

    public void listTasks(Ui ui) {
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markTask(int taskNumber, boolean markAsDone) throws AstaException {
        if (taskNumber >= 0 && taskNumber < tasks.size()) {
            if (markAsDone) {
                tasks.get(taskNumber).markAsDone();
            } else {
                tasks.get(taskNumber).markAsNotDone();
            }
        } else {
            if (markAsDone) {
                AstaException.handleInvalidMarkTaskNumber();
            } else {
                AstaException.handleInvalidUnmarkTaskNumber();
            }
        }
    }

    public void addTodoTask(String description) throws AstaException {
        if (description.isEmpty()) {
            AstaException.handleEmptyTodoDescription();
        }
        tasks.add(new ToDo(description));
    }

    public void addDeadlineTask(String description, String byDateStr) throws AstaException {
        if (description.isEmpty()) {
            AstaException.handleInvalidDeadlineInput();
        }
        try {
            LocalDateTime by = LocalDateTime.parse(byDateStr.trim(), DATE_TIME_FORMATTER);
            tasks.add(new Deadline(description, by));
        } catch (DateTimeParseException e) {
            throw new AstaException("Invalid date format. Please use dd/MM/yyyy HHmm format.");
        }
    }

    public void addEventTask(String description, String fromDateStr, String toDateStr) throws AstaException {
        if (description.isEmpty()) {
            AstaException.handleInvalidEventInput();
        }
        try {
            LocalDateTime from = LocalDateTime.parse(fromDateStr.trim(), DATE_TIME_FORMATTER);
            LocalDateTime to = LocalDateTime.parse(toDateStr.trim(), DATE_TIME_FORMATTER);
            tasks.add(new Event(description, from, to));
        } catch (DateTimeParseException e) {
            throw new AstaException("Invalid date format. Please use dd/MM/yyyy HHmm format.");
        }
    }

    public Task deleteTask(int index) throws AstaException {
        if (index < 0 || index >= tasks.size()) {
            throw new AstaException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    public int getTaskNumber(String input, String action) throws AstaException {
        try {
            return Integer.parseInt(input.substring(action.length() + 1).trim()) - 1;
        } catch (NumberFormatException e) {
            AstaException.handleInvalidTaskNumberFormat(action);
            return -1;
        }
    }
}
