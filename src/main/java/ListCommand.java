public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        if (tasks.isEmpty()) {
            ui.printOutput("Would Ya look at that: No tasks to be found. Shocking ain't it");
        } else {
            ui.printOutput("Look at all these tasks:");
            int i = 1;
            for (Task task : tasks) {
                String line = String.format("%d. %s", i, task);
                ui.printOutput(line);
                i++;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
