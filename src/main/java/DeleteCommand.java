public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks,Ui ui, Storage storage) {
        if (index >= tasks.getLength() || index < 0) {
            ui.showTaskNotFoundError();
            return;
        }
        Task task = tasks.removeTask(index);
        storage.overwriteFile(tasks);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + task.toString());
        String word = tasks.getLength() == 1 ? "task" : "tasks";
        System.out.println(String.format("    Now you have %d %s in the tasks.", tasks.getLength(), word));
    }
}
