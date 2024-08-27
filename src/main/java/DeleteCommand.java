public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        Task removed = tasks.removeTask(index);
        String completeResponse = removed.isDone()
                ? "Great work on completing your task!"
                : "I'm going to assume that task wasn't meant to be there...";

        String listStatus;
        if (tasks.isEmpty()) {
            listStatus = "Well, looks like there is nothing left for you do!";
        } else if (tasks.isAllComplete()) {
            listStatus = String.format("I've ran the numbers, and it says that all %d tasks are complete!",
                    tasks.getCount());
        } else {
            listStatus = String.format("Now get a move on, "
                            + "you have %d out of %d incomplete tasks remaining!",
                    tasks.getIncompleteCount(),
                    tasks.getCount());
        }

        String message = String.format("""
                        %s
                        Proceeding with task removal directive...
                          deleted: %s
                        %s
                        """,
                completeResponse,
                removed,
                listStatus);
        ui.printOutput(message);

        storage.deleteTask(removed, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
