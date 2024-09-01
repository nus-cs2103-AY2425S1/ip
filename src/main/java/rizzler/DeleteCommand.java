package rizzler;

/**
 * Represents a command to the Rizzler chatbot to
 * delete a task from the tasklist.
 */
class DeleteCommand implements Command {
    private final int deleteIndex;

    /**
     * Constructs a new <code>DeleteCommand</code>
     * with the String array parsed by the Parser.
     *
     * @param fullCommand Parsed command from the Parser.
     * @throws RizzlerException If the command's second argument is wrong.
     */
    DeleteCommand(String[] fullCommand) throws RizzlerException {
        if (fullCommand.length == 1) {
            throw new RizzlerException(
                    "Please key in the number of the task to delete\n"
                    + "Format:\n"
                    + "delete [number]");
        }
        try {
            this.deleteIndex = Integer.parseInt(fullCommand[1]) - 1;
        } catch (NumberFormatException e) {
            throw new RizzlerException(
                    "Please ensure that the argument after delete is a number\n"
                    + "Format:\n"
                    + "delete [number]");
        }
    }

    /**
     * Executes the command to delete a task from the tasklist
     * at the index specified.
     *
     * @param tasks TaskList supplied by Rizzler.
     * @param ui Ui supplied by Rizzler.
     * @param fileStorage FileStorage supplied by Rizzler.
     * @return String detailing the deletion.
     * @throws RizzlerException If <code>TaskList</code> throws a <code>RizzlerException</code>.
     */
    public String execute(TaskList tasks,
                        Ui ui,
                        FileStorage fileStorage) throws RizzlerException {
        try {
            String output = tasks.delete(this.deleteIndex);
            fileStorage.save(tasks.getListToSave());
            return output;
        } catch (RizzlerException e) {
            throw e;
        }
    }

    /**
     * Checks if this command is to exit Rizzler.
     *
     * @return <code>false</code> as command does not exit Rizzler.
     */
    public boolean isExit() {
        return false;
    }
}
