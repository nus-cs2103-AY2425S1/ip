package Alex.Command;
import Alex.Exceptions.AlexException;
import Alex.Storage.Storage;
import Alex.Task.TaskList;
import Alex.Ui.Ui;


public interface Command {
    void execute(TaskList tasks, Ui ui, Storage storage) throws AlexException;
    boolean isExit();
}