public class AddTodoCommand extends Command {

    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        TodoTask task = new TodoTask(description);
        tasks.add(task);
        ui.say(String.format("""                
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.""",
                task, tasks.size()));
    }
}
