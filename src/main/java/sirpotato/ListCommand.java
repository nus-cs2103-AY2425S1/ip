package sirpotato;

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listTasks(tasks);
    }
    
}