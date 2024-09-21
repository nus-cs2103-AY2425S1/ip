package gallium.command;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;
import gallium.task.Task;

/**
 * Represents a command to find a task from the task list.
 */
public class FindCommand extends Command {
    private String Message;

    /**
     * Constructs a FindCommand with the message.
     * 
     * @param message The message containing the keyword to filter tasks by.
     */
    public FindCommand(String Message) {
        this.Message = Message;
    }

    /**
     * Executes the find command to filter tasks by the keyword specified in the
     * message.
     * 
     * @param tasklist The list of tasks to execute the command on.
     * @param ui       The user interface that will interact with user.
     * @param storage  The storage that will save any changes made by the command.
     * @throws GalliumException If an error occurs during the execution of the
     *                          command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        try {
            StringBuilder tasksStringBuilder = new StringBuilder();
            String keyword = Message.split("find ")[1];
            for (int i = 1; i < Task.count; i++) {
                Task task = taskList.getTask(i - 1);
                if (task.getDesc().contains(keyword)) {
                    tasksStringBuilder.append("\n    " + task.toString());
                }
            }
            String tasks = tasksStringBuilder.toString();
            ui.printMatchingFind(tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GalliumException("Please put a space after your command! \nExample: find CS2103T");
        }

    }
}
