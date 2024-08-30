package Command;
import Tools.Storage;
import Tools.TaskList;

public class Command {
    TaskList tasks;
    Storage storage;
    String command;

    public Command(TaskList tasks, Storage storage, String command) {
        this.tasks = tasks;
        this.storage = storage;
        this.command = command;
    }
}
