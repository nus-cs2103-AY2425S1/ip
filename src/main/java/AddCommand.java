public class AddCommand extends Command{

    private String taskInfo;
    public AddCommand(String taskInfo) {
        this.taskInfo = taskInfo;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
