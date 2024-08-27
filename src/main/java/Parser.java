import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    public void parseExecute(String input, TaskList taskList, Storage storage, Ui ui) {
        if(input.startsWith("mark")) {
            markCommand(input, taskList, storage, ui);
        } else if(input.startsWith("unmark")) {
            unmarkCommand(input, taskList, storage, ui);
        } else if(input.startsWith("todo")) {
            todoCommand(input, taskList, storage, ui);
        } else if(input.startsWith("deadline")) {
            deadlineCommand(input, taskList, storage, ui);
        } else if(input.startsWith("event")) {
            eventCommand(input, taskList, storage, ui);
        } else if(input.startsWith("delete")) {
            deleteCommand(input, taskList, storage, ui);
        } else if(input.startsWith("list")) {
            listCommand(input, taskList, storage, ui);
        } else if(input.startsWith("bye")) {
            byeCommand(input, taskList, storage, ui);
        } else {
            ui.showErrorUnknownCommand();
        }
    }

    private void markCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        try {
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.getTask(taskIndex).markAsDone();
                storage.saveTasks(taskList.getTasks());
                ui.showTaskMarkedAsDone(taskList.getTask(taskIndex));
            } else {
                ui.showErrorInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            ui.showError("Command must be followed by a specific task number");
        }
    }

    private void unmarkCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        try {
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.getTask(taskIndex).markAsNotDone();
                storage.saveTasks(taskList.getTasks());
                ui.showTaskMarkedAsNotDone(taskList.getTask(taskIndex));
            } else {
                ui.showErrorInvalidTaskNumber();
            }
        } catch (NumberFormatException e) {
            ui.showError("Command must be followed by a specific task number");
        }
    }

    private void todoCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        if (input.length() <= 4) {
            ui.showErrorEmptyTodoDescription();
            return;
        }
        String description = input.substring(5).trim();
        Task task = new ToDo(description);
        taskList.addTask(task);
        storage.saveTasks(taskList.getTasks());
        ui.showTaskAdded(task, taskList.size());
    }

    private void deadlineCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        if (!input.contains("/by")) {
            ui.showError("Deadline format should be: deadline DESCRIPTION /by DATE");
        } if (input.length() == 8) {
            ui.showErrorEmptyDeadlineDescription();
        } else {
            int index = input.indexOf("/");
            String temp = input.substring(index + 1);
            int tempIndex = input.indexOf("y");
            String deadline = input.substring(tempIndex + 2);
            String description = input.substring(9, index);
            Deadline task = new Deadline(description, deadline);
            taskList.addTask(task);
            ui.showTaskAdded(task, taskList.size());
            storage.saveTasks(taskList.getTasks());
        }
    }

    private void eventCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        if (!input.contains("/from") || !input.contains("/to")) {
            ui.showError("event format should be: event DESCRIPTION /from DATE/to DATE");
        }
        if (input.length() == 5) {
            ui.showErrorEmptyEventDescription();
        } else {
            int index = input.indexOf("/");
            String description = input.substring(6, index);
            String temp = input.substring(index + 1);
            int index_2 = temp.indexOf("/");
            int index_m = temp.indexOf("m");
            String dateStart = temp.substring(index_m + 1, index_2);
            String dateEnd = temp.substring(index_2 + 4);
            Event task = new Event(description, dateStart, dateEnd);
            taskList.addTask(task);
            ui.showTaskAdded(task, taskList.size());
            storage.saveTasks(taskList.getTasks());
        }
    }

    private void deleteCommand(String input, TaskList taskList, Storage storage, Ui ui) {
//        if (input.length() < 8 || !Character.isDigit(input.charAt(7))) {
//            ui.showError("Command must be followed by a specific task number");
//        }
        try {
            int indexSpace = input.indexOf(" ");
            int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.getTask(taskIndex).markAsNotDone();
                ui.showTaskRemoved(taskList.getTask(taskIndex), taskList.size() - 1);
                taskList.deleteTask(taskIndex);
                storage.saveTasks(taskList.getTasks());
            } else {
                ui.showErrorInvalidTaskNumber();
            }
        } catch(NumberFormatException e) {
            ui.showError("Command must be followed by a specific task number");
        }
    }

    private void listCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        ui.showTaskList(taskList.getTasks());
    }

    private void byeCommand(String input, TaskList taskList, Storage storage, Ui ui) {
        ui.showGoodbye();
    }
}