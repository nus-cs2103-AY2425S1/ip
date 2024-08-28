public class TodoCommand extends Commands {

    public TodoCommand(String[] split) {
        super(split);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        if (split.length < 2) {
            ui.throwError("???? You're missing the task! Write \"todo <task>\"!");
        }
        ToDoTask toDoTask = new ToDoTask(split[1]);
        tasks.add(toDoTask);
        ui.print("Productive! Added: \n" + toDoTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list!");
    }
}
