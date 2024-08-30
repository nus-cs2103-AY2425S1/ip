public class CommandList extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage){
        ui.printTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
