public class MarkCommand extends Command {

    private int indexOfMark;

    public MarkCommand(int indexOfMark) {
        this.indexOfMark = indexOfMark;
    }
    @Override
    public void run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        try {
            taskList.get(indexOfMark).markAsDone();
            ui.showMarkCommand(taskList, indexOfMark);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            throw new LamaException("Sorry, the number given exceed the bound of list");
        }
    }

}
