package NextGPT.command;
import java.io.IOException;
import NextGPT.TaskList;
import NextGPT.Ui;
import NextGPT.Storage;
import NextGPT.NextGPTException;
import NextGPT.task.Task;

public class AddCommand extends Command{
    protected Task todo;

    public AddCommand(Task todo){
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NextGPTException{
        tasks.add(todo);
        ui.addTask(todo, tasks.size());
        try {
            storage.add_to_memory(tasks);
        } catch(IOException e) {
            throw new NextGPTException("There was an error saving the file. Please try again.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
