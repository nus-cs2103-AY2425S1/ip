package quack.command;

import quack.exception.InvalidCommandException;
import quack.exception.InvalidIndexException;
import quack.tasks.Task;
import quack.util.TaskList;
import quack.util.Ui;

/**
 * This class is responsible for updating the tags of a task in the task list.
 */
public class AddTagCommand extends Command {

    /** List to store all tasks by Quack */
    private TaskList taskList;
    /** Ui to handle all user display interactions */
    private Ui ui;
    /** Index of task to tag*/
    private String index;
    /** Store next prompt */
    private PromptTypes nextPrompt;
    /** Store the types of prompts */
    private enum PromptTypes {
        INDEX,
        TAG,
        DONE
    }

    /**
     * Creates a AddTagCommand object.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param ui The ui object that handles user interface requests.
     */
    public AddTagCommand(TaskList taskList, Ui ui) {
        super();
        this.taskList = taskList;
        this.ui = ui;
        this.nextPrompt = PromptTypes.INDEX;
    }

    @Override
    public void prompt() {
        this.listTasks();
        this.checkEmptyList();
        ui.requestIndexFromUser("tag");
        this.nextPrompt = PromptTypes.TAG;
    }

    @Override
    public void execute(String input) {

        assert(input != null);

        switch (this.nextPrompt) {
        case TAG:
            this.getTagLabelFromUser(input);
            break;
        case DONE:
            this.addLabelToTask(input);
            break;
        default:
            throw new AssertionError();
        }
    }

    /**
     * Lists all the tasks currently in the task list.
     */
    private void listTasks() {
        Command listCommand = new ListCommand(taskList, ui);
        listCommand.prompt();
    }

    /**
     * Checks if the task list is empty.
     * <p>
     * If the task list is empty then set the status of the command to be completed
     * since there is nothing to delete.
     */
    private void checkEmptyList() {
        if (taskList.getLength() == 0) {
            this.completeCommand();
            return;
        }
    }

    /**
     * Requests the tag label for the task.
     * @param index The index of the task to be labeled.
     */
    private void getTagLabelFromUser(String index) {
        this.index = index;
        ui.requestTagLabel();
        this.nextPrompt = PromptTypes.DONE;
    }

    /**
     * Updates the task to be taged with the given label.
     * @param tagLabel The tag label to be assigned to the task.
     */
    private void addLabelToTask(String tagLabel) {

        try {
            int index = Integer.parseInt(this.index);
            Task task = this.taskList.updateTag(index, "addTag", tagLabel);
            ui.printUpdateSuccessfulMessage(task, "tag", taskList);
        } catch (NumberFormatException invalidIdxError) {
            ui.printExceptionMessage(new InvalidIndexException(this.index));
        } catch (IndexOutOfBoundsException indexError) {
            ui.printExceptionMessage(indexError);
        } catch (InvalidCommandException commandError) {
            ui.printExceptionMessage(commandError);
        } finally {
            this.completeCommand();
        }
    }
}
