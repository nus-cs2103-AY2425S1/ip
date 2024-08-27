public class AddCommand extends Command{
    String type;
    String command;
    public AddCommand(String type, String command) {
        super(command);
        this.command = command;
        this.type = type;
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateException, NoDescriptionException{
        switch(type) {
        case "e":
            Events event = Events.CreateEvent(command);
            tasks.addList(event);
            ui.addList(event, tasks.getSize());
            storage.saveItem(tasks.getList());
            break;
        case "t":
            ToDos todo = ToDos.createToDo(command);
            tasks.addList(todo);
            ui.addList(todo, tasks.getSize());
            storage.saveItem(tasks.getList());
            break;
        case "d":
            Deadlines deadline = Deadlines.createDeadline(command);
            tasks.addList(deadline);
            ui.addList(deadline, tasks.getSize());
            storage.saveItem(tasks.getList());
            break;
        }

    }

    @Override
    protected boolean isExit() {
        return false;
    }
}
