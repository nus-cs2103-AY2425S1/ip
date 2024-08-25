package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;
import duck.util.Utils;

public class DeleteCommand extends Command {
    public DeleteCommand(String message) {
        super(message);
    }
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        if (!Utils.isCorrectUpdateFormat(message)) {
            throw new DuckException("Delete tasks with correct format please >:(\n"
                    + "delete {index of task to delete}\n");
        }

        String[] words = message.split(" ");
        try {
            tasks.deleteTask(Integer.parseInt(words[1]) - 1, storage);
        } catch (NumberFormatException e) {
            throw new DuckException("Oops! you have to indicate a valid task index!\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DuckException("Oops! Index out of bound :( Input a valid task index.\n");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
