public class AddCommand implements Command {
    private final String msg;
    private final String[] words;

    AddCommand(String msg) {
        this.msg = msg;
        words = msg.split(" ");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AtreidesException{
        Task newTask = TaskList.getTask(msg, words);
        tasks.addTask(newTask);
        String plural = tasks.size() == 1 ? " task" : " tasks";
        String response = "Task added\n"
                + newTask.toString().indent(2)
                + tasks.size() + plural + " in list\n";
        ui.showMessage(response);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
