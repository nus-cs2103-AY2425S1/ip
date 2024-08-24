public class CommandDelete extends Command{

    private String[] inputs;

    public CommandDelete(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public void execute() {
        if (inputs.length <= 1) {
            Ui.printMessage("The description of a delete cannot be empty, add an index");
            return;
        }

        int index = Integer.parseInt(inputs[1]) - 1;
        if (index < 0 || index >= TaskList.getSize()) {
            Ui.printMessage("Index is out of range, there are only " + TaskList.getSize() + " task(s)");
            return;
        }

        Task task = TaskList.deleteTask(index);
        Ui.printMessage("Noted. I've removed this task:\n   " + task + "\nNow you have " + TaskList.getSize() + " task" + (TaskList.getSize() == 1 ? "" : "s") + " in the list.");
    }

}
