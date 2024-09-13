package rizzler;

/**
 * Represents the command to mark a task as done
 * in Rizzler's tasklist.
 */
class MarkCommand implements Command {
    private final int markIndex;

    /**
     * Constructs a new <code>MarkCommand</code>
     * with the string array parsed by the Parser.
     *
     * @param fullCommand Parsed command from the Parser.
     * @throws RizzlerException If the command's second argument is wrong.
     */
    MarkCommand(String[] fullCommand) throws RizzlerException {
        if (fullCommand.length == 1) {
            throw new RizzlerException(
                    "Please key in the number of the task to mark\n"
                    + "Format:\n"
                    + "mark [number]");
        }
        try {
            this.markIndex = Integer.parseInt(fullCommand[1]) - 1;
        } catch (NumberFormatException e) {
            throw new RizzlerException(
                    "Please ensure that the argument after mark is a number\n"
                    + "Format:\n"
                    + "mark [number]");
        }
    }

    /**
     * Executes the command to mark a task from the tasklist
     * at the index specified.
     *
     * @param tasks TaskList supplied by Rizzler.
     * @param ui Ui supplied by Rizzler.
     * @param fileStorage FileStorage supplied by Rizzler.
     * @return String detailing the marked task.
     * @throws RizzlerException If <code>TaskList</code> throws a <code>RizzlerException</code>.
     */
    public String execute(TaskList tasks,
                        Ui ui,
                        FileStorage fileStorage) throws RizzlerException {
        try {
            String output = tasks.mark(this.markIndex);
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
