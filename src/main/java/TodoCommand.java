public class TodoCommand extends Command {
    String taskString;

    public TodoCommand(String taskString) {
        this.taskString = taskString;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        Todo newTodo = new Todo(taskString);
        tasklist.addTask(newTodo);
        try {
            storage.save(tasklist.getTaskList());
            output.append("Got it. I've added this task:\n").append(newTodo).append("\n").append("Now you have ").append(tasklist.size()).append(" tasks in your list.\n");
            ui.printString(output.toString());
        } catch (AsuraException e) {
            ui.showError(e.getMessage());
        }
    }

    public boolean isExit() {
        return false;
    }
}
