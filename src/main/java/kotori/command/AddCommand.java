package kotori.command;

import java.time.format.DateTimeParseException;
import kotori.storage.Storage;
import kotori.taskList.InvalidInputException;
import kotori.taskList.MissingInformationException;
import kotori.taskList.Task;
import kotori.taskList.TaskList;
import static kotori.ui.Ui.printMessages;

/**
 * This class represents a command of trying to create
 * a task and add it into the task list.
 * */

public class AddCommand extends Command{
    private Storage storage;
    private TaskList taskList;
    private String input;

    public AddCommand (Storage storage, TaskList taskList, String input) {
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
        } catch (MissingInformationException e) {
            return printMessages(e.getMessage());
        } catch (InvalidInputException e) {
            return printMessages(e.getMessage());
        } catch (DateTimeParseException e) {
            return printMessages("Sorry~ I can not recognize the time", "Please enter the time in the " +
                    "YYYY-MM-DD format");
        }
    }
}
