package cypherchatbot.command;

import cypherchatbot.task.Task;
import cypherchatbot.task.ToDo;
import cypherchatbot.util.Storage;
import cypherchatbot.util.TaskList;
import cypherchatbot.util.Ui;

public class ToDoCommand extends Command {
    String[] command;

    public ToDoCommand(String[] s) {
        this.command = s;
    }

    public void execute (TaskList tasks, Ui ui, Storage storage) {
        Task todo = new ToDo(command[1]);
        String output = tasks.addToList(todo);
        ui.output(output);
        storage.addToStorage(todo.toStringinFile());
    }

    public boolean isExit() {
        return false;
    }

}
