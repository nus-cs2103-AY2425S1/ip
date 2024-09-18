package seedu.maxine.command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.maxine.MaxineList;
import seedu.maxine.MaxineStorage;
import seedu.maxine.MaxineUi;
import seedu.maxine.exception.MaxineException;
import seedu.maxine.task.Deadline;
import seedu.maxine.task.Event;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

/**
 * Handles user commands in the Maxine application.
 * It manages the interaction with the task list, storage, and user interface.
 */
public class Command {
    private static boolean isRunning = true;
    private MaxineStorage storage;
    private MaxineUi ui;
    private MaxineList list;

    /**
     * Constructs a new Command instance.
     *
     * @param storage The storage handler for reading from and writing to the file.
     * @param ui The user interface handler for displaying messages to the user.
     * @param list The list of tasks being managed.
     */
    public Command(MaxineStorage storage, MaxineUi ui, MaxineList list) {
        this.storage = storage;
        this.ui = ui;
        this.list = list;
    }

    /**
     * Handles the 'bye' command which stops the application.
     *
     * @param input The user input containing the 'bye' command.
     * @return A goodbye message if the command is properly formatted,
     *     else, return an error message.
     */
    public String handleBye(String input) {
        String[] answer = input.split(" ");
        if (answer.length > 1) {
            return "To say bye to Maxine, just type bye";
        }
        isRunning = false;
        return ui.goodbye();
    }

    /**
     * Handles the 'list' command which returns the list of tasks.
     *
     * @param input The user input containing the 'list' command.
     * @return The list of tasks if the command is properly formatted,
     *     else return an error message.
     */
    public String handleList(String input) {
        String[] answer = input.split(" ");
        if (answer.length > 1) {
            return "To list things out, just type list";
        }
        return ui.showList(list);
    }
    /**
     * Handles the 'mark' command which marks the selected task as done.
     *
     * @param input The user input containing the 'mark' command and task number.
     * @return A message indicating that the task has been marked
     *     if the format is not followed, return an error message.
     */
    public String handleMark(String input) {
        try {
            String[] inputArray = input.split(" ");
            if (inputArray.length != 2) {
                throw new MaxineException("follow this format: mark [task no.]");
            }
            String answer = inputArray[1];
            int mark = Integer.parseInt(answer) - 1;
            Task task = list.get(mark);
            task.markDone();
            storage.refreshStorage(list);
            return ui.mark(task);
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    /**
     * Handles the 'unmark' command which marks the selected task as undone.
     *
     * @param input The user input containing the 'unmark' command and task number.
     * @return A message indicating that the task has been unmarked
     *     if the format is not followed, return an error message.
     */
    public String handleUnmark(String input) {
        try {
            String[] inputArray = input.split(" ");
            if (inputArray.length != 2) {
                throw new MaxineException("follow this format: unmark [task no.]");
            }
            String answer = inputArray[1];
            int mark = Integer.parseInt(answer) - 1;
            Task task = list.get(mark);
            task.markUndone();
            storage.refreshStorage(list);
            return ui.unmark(task);
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    /**
     * Handles the 'todo' command which adds a new todo task to the Tasklist.
     *
     * @param input The user input containing the 'todo' command and task description.
     * @return A message indicating that the task has been added
     *     if the format is not followed, return an error message.
     */
    public String handleTodo(String input) {
        try {
            assert input != null : "input should not be null";
            String[] answer = input.split("todo ");
            String regex = "todo";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 2 || !matcher.find()) {
                throw new MaxineException("Please follow this "
                        + "format: todo [enter maxine.task]");
            }
            String description = answer[1];
            Todo task = new Todo(description);
            list.addTask(task);
            storage.refreshStorage(list);
            return task + " - todo task added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    /**
     * Handles the 'deadline' command which adds a new deadline task.
     *
     * @param input The user input containing the 'deadline' command, task description, and deadline date.
     * @return A message indicating that the deadline task has been added
     *     If the format is not followed, return an error message.
     */
    public String handleDeadline(String input) {
        try {
            assert input != null : "input should not be null";
            String[] answer = input.split("deadline | /by ");
            String regex = "deadline.*?/by";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 3 || !matcher.find()) {
                throw new MaxineException("Please follow this format: deadline "
                        + "[enter maxine.task] /by [enter deadline]");
            }
            String description = answer[1];
            String deadline = answer[2];
            Deadline task = new Deadline(description, deadline);
            list.addTask(task);
            storage.refreshStorage(list);
            return task + " - deadline task added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    /**
     * Handles the 'event' command which adds a new event task.
     *
     * @param input The user input containing the 'event' command, task description, and event date.
     * @return A message indicating that the event task has been added
     *     If the format is not followed, return an error message.
     */
    public String handleEvent(String input) {
        try {
            assert input != null : "input should not be null";
            String[] answer = input.split("event | /from | /to ");
            String regex = "event.*?/from.*?/to";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (answer.length != 4 || !matcher.find()) {
                throw new MaxineException("Please follow this format: event [enter event] "
                        + "/from [start date] /to [end date]");
            }
            String description = answer[1];
            String startTime = answer[2];
            String endTime = answer[3];
            Event task = new Event(description, startTime, endTime);
            list.addTask(task);
            storage.refreshStorage(list);
            return task + " - event added!";
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }
    /**
     * Handles the deletion of a specific task or all tasks based on user input.
     *
     * @param input The input string from the user, expected to be in the format:
     *              "delete [task no.]" or "delete all".
     * @return A message indicating the result of the deletion operation.
     * @throws MaxineException if the input format is incorrect.
     */
    public String handleDelete(String input) {
        try {
            String[] inputArray = input.split(" ");
            if (inputArray.length != 2) {
                throw new MaxineException("follow this format: delete [task no.] "
                        + "or delete all");
            }
            String answer = inputArray[1];
            if (answer.equals("all")) {
                return handleDeleteAll();
            }
            int key = Integer.parseInt(answer) - 1;
            Task task = list.get(key);
            list.delete(key);
            storage.refreshStorage(list);
            return ui.delete(task);
        } catch (MaxineException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the searching of tasks based on the provided input keywords.
     *
     * @param input The input string from the user, expected to start with "find "
     *              followed by the keywords to search for.
     * @return A message indicating the result of the search operation, including
     *         the matching tasks or a message if no tasks are found.
     */
    public String handleFind(String input) {
        ArrayList<Task> currList = storage.load();
        assert currList != null : "currList should not be null";
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : currList) {
            String keywords = input.substring(5);
            if (task.toString().contains(keywords)) {
                tasks.add(task);
            }
        }
        if (tasks.isEmpty()) {
            return "Oh no! I can't find anything on this...";
        }
        return ui.search(tasks);
    }

    /**
     * Handles the deletion of all tasks in the task list.
     *
     * @return A message indicating the result of the delete all operation.
     */
    public String handleDeleteAll() {
        list.deleteAll();
        storage.refreshStorage(list);
        return ui.deleteAll();
    }
    /**
     * Gets the current status of the application.
     *
     * @return true if the application is running, false otherwise.
     */
    public boolean getStatus() {
        return isRunning;
    }
}
