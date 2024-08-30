package duke.parsers;
import duke.tasks.Todo;
import duke.tasks.Event;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.exceptions.MissingDateException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.TaskNotFoundException;
import duke.exceptions.MissingTaskNameException;
import duke.exceptions.InvalidInputException;
import duke.ui.Ui;

public class Parser {
    private TaskList taskList;
    private Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }
    public boolean parse(String userInput) throws InvalidInputException,
            MissingTaskNameException, MissingDateException, TaskNotFoundException, InvalidDateException {
        if (userInput.equals("list")) {
            ui.printMessage(taskList.printList());
        } else if (userInput.equals("bye")) {
            return true;
        } else if (userInput.startsWith("mark")) {
            handleMarkTask(userInput, true);
        } else if (userInput.startsWith("unmark")) {
            handleMarkTask(userInput, false);
        } else if (userInput.startsWith("todo")) {
            handleAddTodo(userInput);
        } else if (userInput.startsWith("deadline")) {
            handleAddDeadline(userInput);
        } else if (userInput.startsWith("event")) {
            handleAddEvent(userInput);
        } else if (userInput.startsWith("delete")) {
            handleDeleteTask(userInput);
        } else if (userInput.startsWith("find")){
            handleFindTask(userInput);
        } else {
            throw new InvalidInputException();
        }
        return false;
    }

    /**
     * mark or mark specific task
     *
     * @param message input of user
     * @param mark boolean to mark or unmark task
     */
    private void handleMarkTask(String message, boolean mark) throws InvalidInputException,
            TaskNotFoundException {
        String[] split = message.split(" ");
        if (split.length > 2) {
            throw new InvalidInputException();
        }
        try {
            if (mark) {
                taskList.markTask(Integer.parseInt(split[1]));
            } else {
                taskList.unmarkTask(Integer.parseInt(split[1]));
            }
        } catch (NumberFormatException e ) {
            ui.printMessage("Invalid number");
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("Index number does not exist");
        }
    }

    /**
     * add todo
     *
     * @param message input of user
     */
    private void handleAddTodo(String message) throws MissingTaskNameException {
        String taskName = message.replace("todo", "").trim();
        if (taskName.isEmpty()) {
            throw new MissingTaskNameException("todo");
        }
        taskList.addTask(new Todo(taskName));
    }

    /**
     * add deadline
     *
     * @param message input of user
     */
    private void handleAddDeadline(String message) throws MissingDateException,
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
        taskList.addTask(new Deadline(taskName, by));

    }

    /**
     * add event to list of Tasks
     *
     * @param message input of user
     */
    private void handleAddEvent(String message) throws MissingDateException,
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
        taskList.addTask(new Event(taskName, from, to));
    }

    /**
     * delete specific event to list of Tasks
     *
     * @param message input of user
     */
    private void handleDeleteTask(String message) throws InvalidInputException,
            TaskNotFoundException {
        String[] split = message.split(" ");
        if (split.length > 2) {
            throw new InvalidInputException();
        }
        try {
            taskList.deleteTask(Integer.parseInt(split[1]));
        } catch (NumberFormatException e ) {
            ui.printMessage("Invalid number");
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("Index number does not exist");
        }
    }

    private void handleFindTask(String message) throws TaskNotFoundException, InvalidInputException {
        String[] split = message.split(" ", 2);
        if (split.length > 2 || split.length < 2) {
            throw new InvalidInputException();
        }
        String keyword = split[1].trim();
        if (keyword.isEmpty()) {
            throw new InvalidInputException();
        }
        taskList.findTask(keyword);
    }

}
