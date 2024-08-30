package sirpotato;

public class FindCommand extends Command {

    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.findTasks(tasks, searchString);
    }

}