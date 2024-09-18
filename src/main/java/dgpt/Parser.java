package dgpt;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;

import dgpt.exception.IncorrectInputException;
import dgpt.exception.TaskNotFoundException;
import dgpt.task.Task;
import dgpt.task.TaskList;

/**
 * The Parser class is responsible for interpreting user input and converting it into commands
 * that interact with the TaskList and Ui components of the Dgpt application.
 * It parses the input string, identifies the command, and performs the corresponding action
 * on the task list while providing feedback to the user interface.
 */
public class Parser {

    /**
     * Parses the user input and executes the corresponding command on the task list.
     * This method identifies the command type and performs the appropriate action,
     * such as marking or unmarking tasks, adding new tasks, or deleting tasks.
     * It also handles exceptions related to incorrect input and task operations.
     *
     * @param text The user input string that contains the command and its parameters.
     * @param taskList The TaskList instance where tasks are managed.
     * @return A string representing the response given by Dgpt after taking in an input.
     * @throws IncorrectInputException If the input format is incorrect or missing required parameters.
     * @throws TaskNotFoundException If the command refers to a task that cannot be found.
     */

    public static String parse(String text, TaskList taskList, Storage storage) throws IncorrectInputException,
            TaskNotFoundException {
        String[] inputs = text.split(" ", 2);
        String command = inputs[0];

        switch (command) {
        case "list" -> {
            return handleListCommand(inputs, taskList);
        }
        case "mark" -> {
            return handleMarkCommand(inputs, taskList);
        }
        case "unmark" -> {
            return handleUnmarkCommand(inputs, taskList);
        }
        case "todo" -> {
            return handleToDoCommand(inputs, taskList);
        }
        case "deadline" -> {
            return handleDeadlineCommand(inputs, taskList);
        }
        case "event" -> {
            return handleEventCommand(inputs, taskList);
        }
        case "delete" -> {
            return handleDeleteCommand(inputs, taskList);
        }
        case "find" -> {
            return handleFindCommand(inputs, taskList);
        }
        case "save" -> {
            return handleSaveCommand(taskList, storage);
        }
        case "recurring" -> {
            return handleRecurringCommand(inputs, taskList);
        }
        default -> {
            return Ui.unknownUi();
        }
        }
    }

    private static String handleListCommand(String[] inputs, TaskList taskList) throws IncorrectInputException {
        if (inputs.length != 1) {
            throw new IncorrectInputException("You should not have anything after your request. (e.g. \"list\")");
        }
        return Ui.listUi(taskList);
    }

    private static String handleMarkCommand(String[] inputs, TaskList taskList) throws IncorrectInputException,
            TaskNotFoundException {
        if (inputs.length != 2) {
            throw new IncorrectInputException("You should have only 1 number after your request. (e.g. \"mark 1\")");
        }
        try {
            int index = Integer.parseInt(inputs[1]) - 1;

            if (index < 0 || index >= taskList.getSize()) {
                throw new TaskNotFoundException("There doesn't seem to be a Task at that position.");
            }

            Task currTask = taskList.markTask(index);
            return Ui.markUi(currTask);
        } catch (NumberFormatException e) {
            return "I'm sorry, your index in invalid";
        }
    }

    private static String handleUnmarkCommand(String[] inputs, TaskList taskList) throws IncorrectInputException,
            TaskNotFoundException {
        if (inputs.length != 2) {
            throw new IncorrectInputException("You should have only 1 number after your request. "
                    + "(e.g. \"unmark 1\")");
        }

        try {
            int index = Integer.parseInt(inputs[1]) - 1;

            if (index < 0 || index >= taskList.getSize()) {
                throw new TaskNotFoundException("There doesn't seem to be a Task at that position.");
            }

            Task currTask = taskList.unmarkTask(index);

            return Ui.unmarkUi(currTask);
        } catch (NumberFormatException e) {
            return "I'm sorry, your index in invalid";
        }
    }

    private static String handleToDoCommand(String[] inputs, TaskList taskList) throws IncorrectInputException {
        if (inputs.length != 2) {
            throw new IncorrectInputException("You should have a description after your request. "
                    + "(e.g. \"todo your_description\")");
        }

        Task addedTask = taskList.addToDoToList(inputs[1]);
        int sizeOfList = taskList.getSize();

        return Ui.addTaskUi(addedTask, sizeOfList);
    }

    private static String handleDeadlineCommand(String[] inputs, TaskList taskList) throws IncorrectInputException {
        if (inputs.length != 2) {
            throw new IncorrectInputException("You should have a description after your request. "
                    + "(e.g. \"deadline your_description /by dd/MM/yyyy\")");
        }

        String[] parts = inputs[1].split(" /by ");

        if (parts.length != 2) {
            throw new IncorrectInputException("You should have a date after your request. "
                    + "(e.g. \"todo your_description /by dd/MM/yyyy\")");
        }

        try {
            String description = parts[0];
            String deadline = parts[1];

            Task addedTask = taskList.addDeadlineToList(description, deadline);
            int sizeOfList = taskList.getSize();

            return Ui.addTaskUi(addedTask, sizeOfList);
        } catch (DateTimeParseException e) {
            return "I'm sorry, your date format is invalid. Try dd/MM/yyyy.";
        }
    }

    private static String handleEventCommand(String[] inputs, TaskList taskList) throws IncorrectInputException {
        if (inputs.length != 2) {
            throw new IncorrectInputException("You should have a description after your request. "
                    + "(e.g. \"event your_description /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\")");
        }

        String[] parts = inputs[1].split(" /");

        if (parts.length != 3) {
            throw new IncorrectInputException("You should have 2 timings after your request. "
                    + "(e.g. \"todo your_description /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm\")");
        }

        try {
            String description = parts[0];
            String startTime = parts[1].substring(5);
            String endTime = parts[2].substring(3);

            Task addedTask = taskList.addEventToList(description, startTime, endTime);
            int sizeOfList = taskList.getSize();


            return Ui.addTaskUi(addedTask, sizeOfList);
        } catch (DateTimeParseException e) {
            return "I'm sorry, your date & time format is invalid. Try dd/MM/yyyy HHmm.";
        }
    }

    private static String handleDeleteCommand(String[] inputs, TaskList taskList) throws IncorrectInputException,
            TaskNotFoundException {
        if (inputs.length != 2) {
            throw new IncorrectInputException("You should have only 1 number after your request. "
                    + "(e.g. \"delete 1\")");
        }

        try {
            int index = Integer.parseInt(inputs[1]) - 1;

            if (index < 0 || index >= taskList.getSize()) {
                throw new TaskNotFoundException("There doesn't seem to be a Task at that position.");
            }

            Task deletedTask = taskList.deleteTask(index);
            int size = taskList.getSize();

            return Ui.deleteUi(deletedTask, size);
        } catch (NumberFormatException e) {
            return "I'm sorry, your index in invalid";
        }
    }

    private static String handleFindCommand(String[] inputs, TaskList taskList) throws IncorrectInputException {
        if (inputs.length != 2) {
            throw new IncorrectInputException("You should input what you're searching for "
                    + "after \"find\" (e.g. \"find task\")");
        }

        List<Task> matchingTasks = taskList.findTasks(inputs[1]);

        return Ui.findUi(matchingTasks);
    }

    private static String handleSaveCommand(TaskList taskList, Storage storage) {
        try {
            storage.save(taskList);
            return Ui.saveUi();
        } catch (IOException e) {
            return Ui.errorUi(e);
        }
    }

    private static String handleRecurringCommand(String[] inputs, TaskList taskList) throws IncorrectInputException {
        if (inputs.length != 2) {
            throw new IncorrectInputException("You should have a description after your request. "
                    + "(e.g. \"recurring your_description /every your_frequency\")");
        }

        String[] parts = inputs[1].split(" /every ", 2);

        if (parts.length != 2) {
            throw new IncorrectInputException("You should have a frequency after your request. "
                    + "(e.g. \"todo your_description /every your_frequency\")");
        }

        String description = parts[0];
        String frequency = parts[1];
        Task addedTask = taskList.addRecurringToList(description, frequency);
        int sizeOfList = taskList.getSize();

        return Ui.addTaskUi(addedTask, sizeOfList);
    }
}
