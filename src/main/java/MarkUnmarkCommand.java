public class MarkUnmarkCommand extends Command {

    int taskIndex;
    Task.Status updatedStatus = Task.Status.NOT_DONE;
    public MarkUnmarkCommand(String input) {
        if (input.startsWith("mark")) {
            this.updatedStatus = Task.Status.DONE;
            String[] tokens = input.split("mark");
            taskIndex = Integer.parseInt(tokens[1].trim());
        } else {
            String[] tokens = input.split("unmark");
            taskIndex = Integer.parseInt(tokens[1].trim());
        }
    }

    public static String getHelpDescription() {
        return "Marks a task as complete or incomplete.\nUsage: mark <index>\n" +
                "Usage: unmark <index>\n " +
                "The index of the task can be found by using the list command";
    }


    /**
     * Marks or unmarks the task as complete.
     */
    @Override
    public void execute() {
        try {
            TaskList.update(taskIndex, updatedStatus);
            Storage.save();
        } catch (LewisException e) {
            Ui.printString(e.getMessage());
        }


    }
}
