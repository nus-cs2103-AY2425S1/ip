public class CommandUnmark extends Command {
    private String[] inputs;

    public CommandUnmark(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(inputs[1]);
        if (index < 1 || index > TaskList.getSize()) {
            Ui.printMessage("Invalid Task number.");
            return;
        }
        Task task = TaskList.getTask(index - 1);
        task.markAsUndone();
    }
}