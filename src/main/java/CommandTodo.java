public class CommandTodo extends Command {
    private String[] inputs;

    public CommandTodo(String[] inputs) {
        this.inputs = inputs;
    }

    @Override
    public void execute() {
        if (inputs.length <= 1) {
            Ui.printMessage("The description of a todo cannot be empty.");
            return;
        }
        TaskList.addTask(new Todo(false, inputs[1]), false);
    }
}
