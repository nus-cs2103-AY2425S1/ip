public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.isEmpty()) {
            ui.showMessage("Your list is empty. Just like my lasagna pan. "
                    + "Are we done here, or are you going to add something?");
        } else {
            String listSummary = "Ugh, here's what you've got so far:\n\n" + taskList.list() +
                    "\nCan we be done now?";
            ui.showMessage(listSummary);
        }
    }
}
