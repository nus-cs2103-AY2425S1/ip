public class UnmarkCommand extends Command {

    private int indexOfUnmark;

    public UnmarkCommand(int indexOfUnmark) {
        this.indexOfUnmark = indexOfUnmark;
    }
    @Override
    public void run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        try {
            taskList.get(indexOfUnmark).markAsUnDone();
            ui.showUnmarkCommand(taskList, indexOfUnmark);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new LamaException("Sorry, the number given exceed the bound of list");
        }
    }

}
