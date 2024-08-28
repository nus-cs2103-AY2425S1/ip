package Gutti;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String input) throws GuttiException {
        try {
            this.index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new GuttiException("Invalid format. Use: delete <task index>");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        try {
            Task removedTask = tasks.getTasks().remove(index);
            storage.saveTasksToFile(tasks.getTasks());  // Pass tasks to save
            ui.showTaskList(tasks);  // Show updated task list
            System.out.println("____________________________________________________________");
            System.out.println("Meow. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + tasks.getTasks().size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
        } catch (IndexOutOfBoundsException e) {
            throw new GuttiException("No such task to delete!");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}