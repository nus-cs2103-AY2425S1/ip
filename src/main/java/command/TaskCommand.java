package command;
import task.Task;
import task.TaskList;
import utilities.Parser;
import utilities.Storage;

/**
 * TaskCommand class is used to add a task to the task list.
 */
public class TaskCommand extends Command {
    private String dialog;
    private TaskList taskList;

    /**
     * Constructor for TaskCommand.
     * @param dialog The dialog from interface providing the task details.
     * @param taskList The task list that the task is to be added to.
     */
    public TaskCommand(String dialog, TaskList taskList) {
        this.dialog = dialog;
        this.taskList = taskList;
    }

    /**
     * Executes the task command.
     */

    @Override
    public void execute() {
        taskList.add(Task.of(dialog));
        new Storage("./data/duke.txt").save(taskList);

        String message = "Got it. I've added this task: \n" 
                + taskList.get(taskList.size() - 1) + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        System.out.println(Parser.addHorizontalLinesAndIndentation(message));
    }
}