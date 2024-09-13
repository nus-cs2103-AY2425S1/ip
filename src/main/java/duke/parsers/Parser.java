package duke.parsers;

import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * The `Parser` class is responsible for interpreting user input and executing
 * the corresponding commands on the task list.
 */
public class Parser {
    private TaskList taskList;

    /**
     * Constructs a `Parser` object with the specified task list and user interface.
     *
     * @param taskList The `TaskList` object that stores the tasks.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses and processes the user's input command.
     *
     * @param userInput The input command provided by the user.
     * @return `true` if the "bye" command is given, signaling the end of the session; `false` otherwise.
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
            return "Please enter numbers only";
        } catch (IndexOutOfBoundsException e) {
            return "Index number you inputted does not exist";
        }
    }

    private String handleAddTodo(String message) throws MissingTaskNameException {
        String taskName = message.replace("todo", "").trim();
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException("todo");
        }
        return taskList.addTask(new Todo(taskName));
    }

    private String handleAddDeadline(String message) throws MissingDateException,
            MissingTaskNameException, InvalidDateException {

        String[] parts = message.split(" /by ");
        String taskName = parts[0].replace("deadline", "").trim();
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException("deadline");
        }
        if (parts.length != 2) {
            throw new MissingDateException("deadline");
        }
        String by = parts[1].trim();
        return taskList.addTask(new Deadline(taskName, by));
    }

    private String handleAddEvent(String message) throws MissingDateException,
            MissingTaskNameException, InvalidDateException {
        String[] parts = message.split(" /from | /to ");
        String taskName = parts[0].replace("event", "").trim();
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException("event");
        }
        if (parts.length != 3) {
            throw new MissingDateException("event");
        }
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
        } catch (NumberFormatException e ) {
            return "Please enter numbers only";
        } catch (IndexOutOfBoundsException e) {
            return "Index number you inputted does not exist";
        }
    }

    private String handleFindTask(String message) throws TaskNotFoundException, InvalidInputException {
        String[] split = message.split(" ", 2);
        if (split.length > 2 || split.length < 2) {
            throw new InvalidInputException();
        }
        String keyword = split[1].trim();
        if (keyword.isEmpty()) {
            throw new InvalidInputException();
        }
        return taskList.findTask(keyword);
    }

}

