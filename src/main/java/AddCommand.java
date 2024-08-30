public class AddCommand extends Command{
    private final String commandType;
    private final String commandPostfix;
    public AddCommand(String commandType, String commandPostfix) {
        super(commandType);
        this.commandType = commandType;
        this.commandPostfix = commandPostfix;
    }
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        switch (this.commandType) {
            case "todo" -> {
                ToDo taskItem = new ToDo(this.commandPostfix);
                storage.save_to_datafile(taskItem);
                ui.showTaskCount();
            }
            case "deadline" -> {
                String[] commandPostfixSplitBy = this.commandPostfix.split("/by ", 2);
                Deadline deadlineItem = new Deadline(commandPostfixSplitBy[0], commandPostfixSplitBy[1]);
                storage.save_to_datafile(deadlineItem);
                ui.showTaskCount();
            }
            case "event" -> {
                String[] SplitFrom = this.commandPostfix.split("/from ", 2);
                String[] SplitTo = SplitFrom[1].split(" /to ", 2);
                Event eventItem = new Event(SplitFrom[0], SplitTo[0], SplitTo[1]);
                storage.save_to_datafile(eventItem);
                ui.showTaskCount();
            }
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
