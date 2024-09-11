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
        } catch (NoSuchElementException e) {
            ui.printLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Returns the default message upon launch.
     * @return Default greeting.
     */
    public String getGreeting() {
        return ui.greet();
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
                Task task = parser.getTask();
                tasks.add(task);
                storage.saveAppend(task);
                return ui.add(task) + "\n" + ui.count(tasks.getSize());
            } else if (commandType.equals("mark")) {
                int index = parser.getIndex();
                tasks.mark(index);
                storage.saveRewrite(tasks);
                return ui.mark(tasks.getTask(index - 1));
            } else if (commandType.equals("unmark")) {
                int index = parser.getIndex();
                tasks.unmark(index);
                storage.saveRewrite(tasks);
                return ui.unmark(tasks.getTask(index - 1));
            } else if (commandType.equals("delete")) {
                int index = parser.getIndex();
                Task task = tasks.getTask(index - 1);
                tasks.delete(index);
                storage.saveRewrite(tasks);
                return ui.delete(task) + "\n" + ui.count(tasks.getSize());
            } else if (commandType.equals("find")) {
                String keyword = parser.getString();
                return ui.list(tasks.find(keyword));
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


