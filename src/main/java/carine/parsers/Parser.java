package carine.parsers;

import carine.exceptions.*;
import carine.tasks.Deadline;
import carine.tasks.Event;
import carine.tasks.TaskList;
import carine.tasks.Todo;
import carine.ui.Ui;

/**
 * The `Parser` class is responsible for interpreting user input and executing
 * the corresponding commands on the task list.
 */
public class Parser {
    private static final int VALID_ADD_DEADLINE_LENGTH = 2;
    private static final int VALID_ADD_EVENT_LENGTH = 3;
    private TaskList taskList;

    /**
     * Constructs a `Parser` object with the specified TaskList.
     *
     * @param taskList The `TaskList` object that stores the tasks of users.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses and processes the user's input command.
     *
     * @param userInput The input command provided by the user.
     * @return output to user.
     * @throws InvalidInputException If the input command is not recognized.
     * @throws MissingTaskNameException If a task name is missing when adding a task.
     * @throws MissingDateException If a date is missing when adding a deadline or event task.
     * @throws TaskNotFoundException If a task is not found when attempting to mark, unmark, or delete it.
     * @throws InvalidDateException If the date format is invalid when adding a deadline or event task.
     */
    public String parse(String userInput) throws InvalidInputException, MissingTaskNameException,
            MissingDateException, TaskNotFoundException, InvalidDateException {

        if (userInput.equals("list")) {
            return taskList.printList();
        } else if (userInput.equals("bye")) {
            return "exit";
        } else if (userInput.startsWith("mark")) {
            return handleMarkTask(userInput, true);
        } else if (userInput.startsWith("unmark")) {
            return handleMarkTask(userInput, false);
        } else if (userInput.startsWith("todo")) {
            return handleAddTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            return handleAddDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            return handleAddEvent(userInput);
        } else if (userInput.startsWith("delete")) {
            return handleDeleteTask(userInput);
        } else if (userInput.startsWith("find")) {
            return handleFindTask(userInput);
        } else if (userInput.equals("command")) {
            return Ui.printCommand();
        } else {
            throw new InvalidInputException();
        }
    }

    private String handleMarkTask(String message, boolean mark) throws InvalidInputException,
            TaskNotFoundException {
        String[] split = message.split(" ");
        if (split.length > 2) {
            throw new InvalidInputException();
        }
        try {
            if (mark) {
                return taskList.markTask(Integer.parseInt(split[1]));
            } else {
                return taskList.unmarkTask(Integer.parseInt(split[1]));
            }
        } catch (NumberFormatException e) {
            return "ERROR: Please enter numbers only";
        } catch (IndexOutOfBoundsException e) {
            return "ERROR: Index number you inputted does not exist";
        }
    }

    private String handleAddTodo(String message) throws MissingTaskNameException {
        String taskName = validateTaskName(message, "todo");
        return taskList.addTask(new Todo(taskName));
    }

    private String handleAddDeadline(String message) throws MissingDateException,
            MissingTaskNameException, InvalidDateException {
        String[] parts = splitAddTaskUserInput(message, "deadline");
        validateDate(parts, "deadline");
        String taskName = validateTaskName(parts[0], "deadline");
        String by = parts[1].trim();
        return taskList.addTask(new Deadline(taskName, by));
    }

    private String handleAddEvent(String message) throws MissingDateException,
            MissingTaskNameException, InvalidDateException {
        String[] parts = splitAddTaskUserInput(message, "event");
        validateDate(parts, "event");
        String taskName = validateTaskName(parts[0], "event");
        String from = parts[1].trim();
        String to = parts[2].trim();
        return taskList.addTask(new Event(taskName, from, to));
    }

    private String handleDeleteTask(String message) throws InvalidInputException,
            TaskNotFoundException {
        String[] split = message.split(" ");
        if (split.length > 2) {
            throw new InvalidInputException();
        }
        try {
            return taskList.deleteTask(Integer.parseInt(split[1]));
        } catch (NumberFormatException e) {
            return "ERROR: Please enter numbers only";
        } catch (IndexOutOfBoundsException e) {
            return "ERROR: Index number you inputted does not exist";
        }
    }

    private String handleFindTask(String message) throws TaskNotFoundException, InvalidInputException {
        String[] parts = message.split(" ", 2);
        if (parts.length != 2 || parts[1].isEmpty()) {
            throw new InvalidInputException();
        }
        String keyword = parts[1].trim();
        return taskList.findTask(keyword);
    }

    private String validateTaskName(String message, String taskType) throws MissingTaskNameException {
        String taskName = message.replaceFirst(taskType, "").trim();

        if (taskName.isEmpty()) {
            throw new MissingTaskNameException(taskType);
        }

        return taskName;
    }

    private void validateDate(String[] parts, String taskType) throws MissingDateException {
        if (taskType.equals("deadline") && parts.length != VALID_ADD_DEADLINE_LENGTH) {
            throw new MissingDateException("deadline");
        }
        if (taskType.equals("event") && parts.length != VALID_ADD_EVENT_LENGTH) {
            throw new MissingDateException("event");
        }
    }

    private String[] splitAddTaskUserInput(String message, String command) {
        String[] parts = {};
        if (command.equals("deadline")) {
            parts = message.split(" /by ");
        }
        if (command.equals("event")) {
            parts = message.split(" /from | /to ");
        }
        return parts;
    }
}


