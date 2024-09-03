public class DeleteCommand extends Command {
    private String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws ToothlessExceptions {
        if(description.isEmpty()) {
            throw new MissingIndex("delete", "delete <index>");
        }
        int deleteIndex = Integer.parseInt(description);
        taskList.deleteTask(deleteIndex);
        storage.saveTask(taskList.list);
    }
}
