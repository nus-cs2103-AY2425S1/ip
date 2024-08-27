public class DeleteTaskCommand extends Command{

    /**
     * Creates a DeleteTaskCommand object.
     */
    public DeleteTaskCommand() {

    }

    @Override
    public void execute(Quack quack, TaskList taskList, Storage storage) {
        
        Command listCommand = new ListCommand();
        listCommand.execute(quack, taskList, storage);

        if (taskList.getLength() != 0) {
            System.out.println("Which task do you want to delete? (Input the index of the task)");
            String index = quack.sc.nextLine();
    
            try {
                taskList.deleteTask(index);
            } catch (InvalidIndexException indexError) {
                System.out.println(indexError.getMessage());
            }
        }
    }
}
