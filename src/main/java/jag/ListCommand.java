package jag;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            list.append(i + 1).append(". ").append(task.toString()).append("\n");
        }
        ui.list(list.toString());
    }

    @Override
    public Boolean isExit() {
        return false;
    }
}
