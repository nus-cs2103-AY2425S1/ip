public class ToDoCommand extends AddCommand {

    public ToDoCommand(String s) {
        super(s);
    }

    @Override
    public void execute(TaskList tasks, BingBongUI ui, Storage storage) {
        Task task = new Todo(description);
        tasks.add(task);
        saveTasks(tasks, ui, storage);
        printAddMessage(ui, tasks, task);
    }
}
