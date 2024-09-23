package fence;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import fence.parser.Parser;
import fence.storage.Storage;
import fence.task.Task;
import fence.tasklist.TaskList;
import fence.ui.Ui;
import javafx.application.Platform;

/**
 * Represents the chatbot Fence.
 */
public class Fence {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private boolean hasCorruptedData;
    private boolean hasPermission;
    private boolean isExit = false;

    /**
     * Constructs an instance of Fence with the task list loaded in from the storage.
     * If the storage is unable to be read successfully, it is initialised with an empty task list instead.
     */
    public Fence() {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        try {
            tasks = new TaskList(storage.read(parser));
            hasCorruptedData = false;
            hasPermission = true;
        } catch (NoSuchElementException e) {
            tasks = new TaskList(new ArrayList<>());
            hasCorruptedData = true;
            hasPermission = true;
        } catch (IOException e) {
            tasks = new TaskList(new ArrayList<>());
            hasCorruptedData = false;
            hasPermission = false;
        }
    }

    /**
     * Returns the default greeting and reminder for incomplete deadline tasks due the current day.
     * If data was not correctly loaded, a notification will be returned instead.
     * @return Default message upon launch.
     */
    public String getGreeting() {
        String greetMessage = ui.greet();
        if (!hasPermission) {
            String permissionError = ui.printPermissionError();
            return greetMessage + "\n" + permissionError;
        }
        if (hasCorruptedData) {
            String loadingError = ui.printLoadingError();
            return greetMessage + "\n" + loadingError;
        }
        TaskList dueTasks = tasks.findDue();
        String reminderMessage = ui.remind(dueTasks);
        return greetMessage + "\n" + reminderMessage;
    }

    /**
     * Returns the default response for adding a task. Handles the actual addition of the task in the tasklist and
     * storage.
     * @return Default response for adding a task.
     * @throws IOException If storage was unable to be successfully updated.
     */
    public String getTaskResponse() throws IOException {
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
     * @throws IOException If the storage was unable to be successfully updated.
     * @throws ArrayIndexOutOfBoundsException If the given index is outside of the array range.
     */
    public String getMarkResponse() throws IOException, IndexOutOfBoundsException {
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
     * @throws IOException If the storage was unable to be successfully updated.
     * @throws ArrayIndexOutOfBoundsException If the given index is outside of the array range.
     */
    public String getUnmarkResponse() throws IOException, IndexOutOfBoundsException {
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
     * @throws IOException If the storage was unable to be successfully updated.
     * @throws ArrayIndexOutOfBoundsException If the given index is outside of the array range.
     */
    public String getDeleteResponse() throws IOException, IndexOutOfBoundsException {
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
     * Returns the default exit message and sets the application to be ready to exit upon next user input.
     * @return Default exit message.
     */
    public String getExitResponse() {
        isExit = true;
        return ui.exit();
    }

    /**
     * Returns the appropriate response for the respective commands inputted.
     * If the commands were unable to be carried out successfully, the appropriate error messages will be returned.
     * If the user is ready to exit, the next input will stop and close the application.
     * @param command User input.
     * @return Default response for that particular command type.
     */
    public String getResponse(String command) {
        if (isExit) {
            Platform.exit();
        }
        try {
            String commandType = parser.parseInput(command);
            if (commandType.equals("bye")) {
                return getExitResponse();
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
            } else if (commandType.equals("unknown command")) {
                return ui.printUnknownCommand();
            } else {
                throw new AssertionError("commandType should be one of the default strings");
            }
        } catch (NoSuchElementException e) {
            return ui.printMissingFieldError();
        } catch (DateTimeParseException e) {
            return ui.printInvalidDateError();
        } catch (NumberFormatException e) {
            return ui.printInvalidNumberError();
        } catch (IOException e) {
            return ui.printPermissionError();
        } catch (IndexOutOfBoundsException e) {
            return ui.printInvalidIndexError();
        }
    }
}


