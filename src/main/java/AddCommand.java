public class AddCommand extends Command {
    private String desc;

    public AddCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws GalliumException {
        if (desc.startsWith("todo ")) {
            Todo todo = new Todo(desc);
            ui.printAddTodo(todo);
            taskList.add(todo);
            Task.count++;
        } else if (desc.startsWith("deadline ")) {
            Deadline deadline = new Deadline(desc);
            ui.printAddDeadline(deadline);
            taskList.add(deadline);
            Task.count++;
        } else if (desc.startsWith("event ")) {
            Event event = new Event(desc);
            ui.printAddEvent(event);
            taskList.add(event);
            Task.count++;
        }
        storage.writeFile(ui);
    }
}
