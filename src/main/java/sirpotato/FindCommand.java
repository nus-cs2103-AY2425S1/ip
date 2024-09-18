package sirpotato;

public class FindCommand extends Command {

    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.findTasks(tasks, searchString);
    }

}