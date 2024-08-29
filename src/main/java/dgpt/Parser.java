package dgpt;

import dgpt.exception.IncorrectInputException;
import dgpt.exception.TaskNotFoundException;
import dgpt.task.TaskList;

import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for interpreting user input and converting it into commands
 * that interact with the TaskList and Ui components of the DGPT application.
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
     * @param input The user input string that contains the command and its parameters.
     * @param taskList The TaskList instance where tasks are managed.
     * @param ui The Ui instance used to display messages and task information to the user.
     * @throws IncorrectInputException If the input format is incorrect or missing required parameters.
     * @throws TaskNotFoundException If the command refers to a task that cannot be found.
     */
    public static void parse(String input, TaskList taskList, Ui ui) throws IncorrectInputException, TaskNotFoundException {
        String[] command = input.split(" ", 2);

        switch (command[0]) {
        case "list" -> {
            if (command.length == 1) {
                ui.showList(taskList);
            } else {
                throw new IncorrectInputException("OOPS!!! You should not have anything after your request. " +
                        "(e.g. \"list\")");
            }
        }
        case "mark" -> {
            if (command.length == 2) {
                ui.showMark(taskList.markTask(Integer.parseInt(command[1]) - 1));
            } else {
                throw new IncorrectInputException("OOPS!!! You should have only 1 number after your request. " +
                        "(e.g. \"mark 1\")");
            }
        }
        case "unmark" -> {
            if (command.length == 2) {
                ui.showUnmark(taskList.unmarkTask(Integer.parseInt(command[1]) - 1));
            } else {
                throw new IncorrectInputException("OOPS!!! You should have only 1 number after your request. " +
                        "(e.g. \"unmark 1\")");
            }
        }
        case "todo" -> {
            if (command.length == 2) {
                ui.showTask(taskList.addToDoToList(command[1]),taskList.getSize());
            } else {
                throw new IncorrectInputException("OOPS!!! You should have a description after your request. " +
                        "(e.g. \"todo your_description\")");
            }
        }
        case "deadline" -> {
            try {
                if (command.length == 2) {
                    String[] parts = command[1].split(" /by ");
                    ui.showTask(taskList.addDeadlineToList(parts[0], parts[1]), taskList.getSize());
                } else {
                    throw new IncorrectInputException("OOPS!!! You should have a description after your request. " +
                            "(e.g. \"todo your_description /by your_deadline\")");
                }
            } catch (DateTimeParseException e) {
                ui.showError(e);
            }
        }
        case "event" -> {
            try {
                if (command.length == 2) {
                    String[] parts = command[1].split(" /");
                    ui.showTask(taskList.addEventToList(parts[0], parts[1].substring(5),
                            parts[2].substring(3)), taskList.getSize());
                } else {
                    throw new IncorrectInputException("OOPS!!! You should have a description after your request. " +
                            "(e.g. \"todo your_description /from your_start_time /to your_end_time\")");
                }
            } catch (DateTimeParseException e) {
                ui.showError(e);
            }
        }
        case "delete" -> {
            if (command.length == 2) {
                int size = taskList.getSize();
                ui.showDelete(taskList.deleteTask(Integer.parseInt(command[1]) - 1), size);
            } else {
                throw new IncorrectInputException("OOPS!!! You should have only 1 number after your request. " +
                        "(e.g. \"delete 1\")");
            }
        }
        default -> throw new TaskNotFoundException("OOPS!!! I do not recognise that request. These are the " +
                "following requests that are supported:\n" +
                "-list\n" +
                "-mark\n" +
                "-unmark\n" +
                "-todo\n" +
                "-deadline\n" +
                "-event\n" +
                "-delete\n" +
                "-bye");
        }
    }
}
