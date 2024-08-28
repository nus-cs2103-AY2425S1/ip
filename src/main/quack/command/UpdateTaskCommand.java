package quack.command;

/**
 * This class is responsible for updating of tasks in the task list.
 */
public class UpdateTaskCommand extends Command{
    
    /** Update command that the user wants to execute */
    private String command;

    /**
     * Creates a UpdateTaskCommand object.
     * @param command What the user wants to update.
     */
    public UpdateTaskCommand (String command) {
        this.command = command;
    }

    @Override
    public void execute(Quack quack, TaskList taskList, Storage storage, Ui ui) {

        Command listCommand = new ListCommand();
        listCommand.execute(quack, taskList, storage, ui);

        if (taskList.getLength() != 0) {
            try {
                int index = ui.requestIndexFromUser(this.command);
                Task task = taskList.updateTask(index, this.command);
                ui.printUpdateSuccessfulMessage(task, command, taskList);
            } catch (InvalidIndexException invalidIdxError) {
                ui.printExceptionMessage(invalidIdxError);
            } catch (IndexOutOfBoundsException indexError) {
               ui.printExceptionMessage(indexError);;
            } catch (FailedUpdateException failUpdateError) {
                ui.printExceptionMessage(failUpdateError);
            }
        }
    }
}
