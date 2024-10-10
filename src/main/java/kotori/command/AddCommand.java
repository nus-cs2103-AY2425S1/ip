package kotori.command;
import static kotori.ui.Ui.printMessages;

import java.time.format.DateTimeParseException;

import kotori.parser.InvalidNumberOfArgumentException;
import kotori.storage.Storage;
import kotori.tasklist.InvalidInputException;
import kotori.tasklist.MissingInformationException;
import kotori.tasklist.Task;
import kotori.tasklist.TaskList;



/**
 * This class represents a command of trying to create
 * a task and add it into the task list.
 * */

public class AddCommand extends Command {
    private Storage storage;
    private TaskList taskList;
    private String input;

    /**
     * Create an add-command
     * */
    public AddCommand(Storage storage, TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
        this.storage = storage;
    }

    /**
     * Activates the command the try to create
     * and add a new task to the task list.
     * */

    @Override
    public String execute() {
        try {
            Task task = Task.of(input);
            taskList.add(task);
            storage.updateFile(taskList);
            return printMessages(new String[]{"Got it. I've added this task:", task.toString(),
                    String.format("Now you have %s tasks in the list", taskList.size())});
        } catch (InvalidInputException | InvalidNumberOfArgumentException | MissingInformationException e) {
            return printMessages(e.getMessage());
        } catch (DateTimeParseException e) {
            return printMessages("Sorry~ I can not recognize the time", "Please enter the time in the "
                    + "YYYY-MM-DD format");
        }
    }
}
