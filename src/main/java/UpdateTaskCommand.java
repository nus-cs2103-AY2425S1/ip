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
    public void execute(Quack quack, TaskList taskList, Storage storage) {

        String index = null;

        Command listCommand = new ListCommand();
        listCommand.execute(quack, taskList, storage);

        if (taskList.getLength() != 0) {
            switch (this.command) {
            case "mark":
                System.out.println("Which task do you want to mark? (Input the index of the task)");
                index = quack.sc.nextLine();
                
                break;
            case "unmark":
                System.out.println("Which task do you want to unmark? (Input the index of the task)");
                index = quack.sc.nextLine();
            }
    
            try {
                taskList.updateTask(index, this.command);
            } catch (InvalidIndexException indexError) {
                System.out.println(indexError.getMessage());
            }
        }
    }
}
