public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskHandler taskHandler, Ui ui) throws IllegalGingerArgumentException {
        if (index < 0 || index > taskHandler.taskCount() - 1) {
            throw new IllegalGingerArgumentException(String.format("You entered an invalid task number! %s",
                    taskHandler.taskCount() == 0
                            ? "You have no tasks yet, do add one!"
                            : taskHandler.taskCount() == 1
                            ? "Your only task number is 1, since you only have one task."
                            : "You can enter task number from 1 - " + taskHandler.taskCount()
                        ));
        }

        taskHandler.changeTaskStatus(index, true);
        Task t = taskHandler.getTask(index);
        ui.outputMessage("Nice! I've marked this task as done:\n  " + t);
    }
}
