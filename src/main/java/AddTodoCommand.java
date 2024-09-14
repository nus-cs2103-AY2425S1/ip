public class AddTodoCommand extends Command {

    public AddTodoCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String command = getDescription();

        String taskDescription = Parser.splitCommandAndTaskDescription(command);
        Task newTodo = new Todo(taskDescription);
        String addedTodo = tasks.addTask(newTodo);
        System.out.println(addedTodo);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
