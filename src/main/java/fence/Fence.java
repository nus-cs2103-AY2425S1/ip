package fence;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import fence.parser.Parser;
import fence.storage.Storage;
import fence.task.Task;
import fence.tasklist.TaskList;
import fence.ui.Ui;

/**
 * Represents the chatbot Fence.
 */
public class Fence {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private boolean hasData;

    /**
     * Constructs an instance of Fence with the task list loaded in from the storage.
     * If the data file is corrupted, it is initialised with an empty task list instead.
     */
    public Fence() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.read(parser));
            hasData = true;
        } catch (NoSuchElementException e) {
            tasks = new TaskList(new ArrayList<>());
            hasData = false;
        }
    }

    /**
     * Returns the default greeting.
     * If data was not correctly loaded, a notification will be returned as well.
     * @return Default message upon launch.
     */
    public String getGreeting() {
        return ui.greet() + (hasData ? "" : "\n" + ui.printLoadingError());
    }

    /**
     * Returns the default response for adding a task. Handles the actual addition of the task in the tasklist and
     * storage.
     * @return Default response for adding a task.
     */
    public String getTaskResponse() {
        Task task = parser.getTask();
        tasks.add(task);
        storage.saveAppend(task);
        String addMessage = ui.add(task);
        String countMessage = ui.count(tasks);
        return addMessage + "\n" + countMessage;
    }

    /**
     * Returns the default response for marking a task. Handles the actual marking of the task in the tasklist and
     * storage.
     * @return Default response for marking a task.
     */
    public String getMarkResponse() {
        int index = parser.getIndex();
        Task task = tasks.getTask(index - 1);
        tasks.mark(index);
        storage.saveRewrite(tasks);
        return ui.mark(task);
    }

    /**
     * Returns the default response for unmarking a task. Handles the actual unmarking of the task in the tasklist and
     * storage.
     * @return Default response for unmarking a task.
     */
    public String getUnmarkResponse() {
        int index = parser.getIndex();
        Task task = tasks.getTask(index - 1);
        tasks.unmark(index);
        storage.saveRewrite(tasks);
        return ui.unmark(task);
    }

    /**
     * Returns the default response for deleting a task. Handles the actual deletion of the task in the tasklist and
     * storage.
     * @return Default response for deleting a task.
     */
    public String getDeleteResponse() {
        int index = parser.getIndex();
        Task task = tasks.getTask(index - 1);
        tasks.delete(index);
        storage.saveRewrite(tasks);
        String deleteMessage = ui.delete(task);
        String countMessage = ui.count(tasks);
        return deleteMessage + "\n" + countMessage;
    }

    /**
     * Returns the default response for searching for tasks.
     * @return Default response for searching for tasks.
     */
    public String getFindResponse() {
        String keyword = parser.getString();
        TaskList matchingTasks = tasks.find(keyword);
        return ui.list(matchingTasks);
    }

    /**
     * Returns the appropriate response for the respective commands inputted.
     * @param command User input.
     * @return Default response for that particular command type.
     */
    public String getResponse(String command) {
        try {
            String commandType = parser.parseInput(command);
            if (commandType.equals("bye")) {
                return ui.exit();
            } else if (commandType.equals("list")) {
                return ui.list(tasks);
            } else if (commandType.equals("task")) {
                return getTaskResponse();
            } else if (commandType.equals("mark")) {
                return getMarkResponse();
            } else if (commandType.equals("unmark")) {
                return getUnmarkResponse();
            } else if (commandType.equals("delete")) {
                return getDeleteResponse();
            } else if (commandType.equals("find")) {
                return getFindResponse();
            } else {
                return ui.printUnknownCommand();
            }
        } catch (NoSuchElementException e) {
            return ui.printMissingFieldError();
        } catch (DateTimeParseException e) {
            return ui.printInvalidDateError();
        } catch (NumberFormatException e) {
            return ui.printInvalidNumberError();
        }
    }
}


