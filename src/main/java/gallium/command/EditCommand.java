package gallium.command;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

import gallium.main.GalliumException;
import gallium.main.Storage;
import gallium.main.TaskList;
import gallium.main.Ui;
import gallium.task.Deadline;
import gallium.task.Event;
import gallium.task.Task;
import gallium.task.Todo;



/**
 * Represents a command to amend a task in the task list.
 */
public class EditCommand extends Command {

    private String message;
    private int index;
    private boolean isFieldSelected = false;
    private Task selectedTask;
    private Ui ui;

    /**
     * Constructs a MarkCommand with the specified task.
     * 
     * @param message The message containing the tasks.
     */
    public EditCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the mark command to mark or unmark a task.
     * 
     * @param tasklist The list of tasks to execute the command on.
     * @param ui       The user interface that will interact with user.
     * @param storage  The storage that will save any changes made by the command.
     * @throws GalliumException If an error occurs during the execution of the
     *                          command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        if (!isFieldSelected) {
            try {
                this.index = Integer.valueOf(message.split(" ")[1]) - 1;
                this.selectedTask = taskList.getTask(index);
                this.ui = ui;
                ui.printShowTask(selectedTask);
                ui.printAskEditField(selectedTask);
                this.isFieldSelected = true;
            } catch (IndexOutOfBoundsException e) {
                ui.showWrongIndex();
            }
        } else {
            try {
                parseEdit(message);
                ui.printEditMessage(selectedTask);
                storage.writeFile(ui);
                this.isFieldSelected = false;
            } catch (ParseException | DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                ui.showWrongDateTimeFormat();
            }
        }
    }

    private void parseEdit(String message) throws GalliumException, ParseException, DateTimeParseException {
        try {
            if (selectedTask instanceof Todo) {
                selectedTask.setDesc(message);
            } else if (message.startsWith("1. ")) {
                selectedTask.setDesc(message.substring(3));
            } else if (message.startsWith("2. ")) {
                if (selectedTask instanceof Deadline) {
                    selectedTask.setBy(message.substring(3));
                } else if (selectedTask instanceof Event) {
                    selectedTask.setFrom(message.substring(3));
                } 
            } else if (message.startsWith("3. ")) {
                    selectedTask.setTo(message.substring(3));
            } else {
                throw new GalliumException("I don't understand!! \nPlease enter your input again!");
            }
        } catch (ParseException | DateTimeParseException e) {
            throw e;
        }
    }

    public boolean getIsFieldSelected() {
        return this.isFieldSelected;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}