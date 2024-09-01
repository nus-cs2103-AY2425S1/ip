import exceptions.AstorException;
import exceptions.DeleteTaskNumberException;

public class DeleteCommand extends Command {
    private String info;

    public DeleteCommand(String info) {
        this.info = info;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AstorException {
        String formattedString = info.substring(6).trim();
        int indexC = -1;
        try {
            indexC = Integer.parseInt(formattedString);
        } catch (NumberFormatException e) {
            throw new DeleteTaskNumberException();
        }
        taskList.deleteTask(indexC, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
