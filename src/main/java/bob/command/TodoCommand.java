package bob.command;

import bob.exception.InvalidTaskException;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.task.Todo;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class TodoCommand extends Command {

    /**
     * Constructor to initalise TodoCommand
     *
     * @param input
     */
    public TodoCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = this.getInput();
        String[] inputArray = input.split("\s+");
        try {
            String taskDescription = taskList.getDescriptionOnly(input);
            if (taskDescription.equals("")) {
                throw new InvalidTaskException("OOPS!!! The description of a todo task cannot be empty.");
            }
            Task newTask = new Todo(taskDescription);
            //Update taskList instance by adding the new task.
            taskList.getAllRecords().add(taskList.getRecordSize(), newTask);
            taskList.incrementLatestRecordedIndex();
            //save task
            storage.saveRecordsToStorage(taskList.getAllRecords());
            //print confirmation
            String todoCommandString = taskList.getAddedTaskString();
            //return string representation of successful adding / unsuccessful adding.
            Ui.printLines(todoCommandString);
            return todoCommandString;
        } catch (InvalidTaskException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }
}
