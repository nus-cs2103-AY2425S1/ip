package Gutti;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String input) throws GuttiException {
        if (input.length() <= 5) {
            throw new GuttiException("Invalid format. Use: todo <task description>");
        }
        this.description = input.substring(0).trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        Task todoTask = new Todo(description, false);
        tasks.addTask(todoTask);  // Corrected method name
        storage.saveTasksToFile(tasks.getTasks());  // Pass tasks to save
        ui.showTaskList(tasks);  // Show updated task list
    }
    @Override
    public boolean isExit() {
        return false;
    }
}