public class DeleteCommand extends Command{

    /** The index of the (0-indexed) task to be deleted */
    private final int index;

    /**
     * Private constructor for a delete command
     * @param index 0-indexed index of the task
     */
    private DeleteCommand(int index) {
        this.index = index;
    }

    public static DeleteCommand of(String input) throws LewisException{
        if (TaskList.getLength() == 0) {
            throw new LewisException("Hey, the tasklist is empty. You can't delete" +
                    "something from an empty list!");
        }
        try {
            String[] tokens = input.split(" ");
            int index = Integer.parseInt(tokens[1]) - 1;
            if (index < 0 || index >= TaskList.getLength()) {
                throw new LewisException(String.format(
                        "Hey, enter a valid number between 1 and %d",
                        TaskList.getLength()));
            }
            return new DeleteCommand(index);
        } catch(NumberFormatException e) {
            throw new LewisException(
                    String.format("Hey, enter a valid number between 1 and %d",
                            TaskList.getLength()));
        }
    }

    /**
     * Returns a description for the user on the usage of the delete command
     * @return a string description
     */
    public static String getHelpDescription() {
        return """
                Deletes a task from the tasklist given a specified index
                Usage: delete <index>
                To find the index of the task you want to delete, try
                "list\"""";
    }
    /**
     * Deletes the indicated task from the task list, specified by its index.
     */
    @Override
    public void execute() {
        Ui.printDeletedTask(TaskList.getTask(index));
        TaskList.delete(index);
    }
}
