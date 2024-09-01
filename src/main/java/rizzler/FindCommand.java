package rizzler;

/**
 * Represents a command to Rizzler to find tasks
 * with names matching a given keyword.
 */
class FindCommand implements Command {
    private final String[] fullCommand;

    /**
     * Constructs a new <code>FindCommand</code> object.
     *
     * @param fullCommand Parsed command from the Parser.
     * @throws RizzlerException If the second argument of the command is missing.
     */
    FindCommand(String[] fullCommand) throws RizzlerException {
        if (fullCommand.length < 2) {
            throw new RizzlerException("Please enter the string to find");
        }
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the command to find tasks matching a
     * given keyword.
     *
     * @param tasks TaskList supplied by Rizzler.
     * @param ui Ui supplied by Rizzler.
     * @param fileStorage FileStorage supplied by Rizzler.
     * @return String detailing tasks found.
     * @throws RizzlerException If the tasklist has no tasks.
     */
    public String execute(TaskList tasks,
                        Ui ui,
                        FileStorage fileStorage) throws RizzlerException {
        String keyword = this.fullCommand[1];
        for (int i = 2; i < this.fullCommand.length; i++) {
            keyword += " " + this.fullCommand[i];
        }
        try {
            String output = tasks.find(keyword);
            return output;
        } catch (RizzlerException e) {
            throw e;
        }
    }

    /**
     * Checks if this command is to exit Rizzler.
     *
     * @return false as command does not exit Rizzler.
     */
    public boolean isExit() {
        return false;
    }
}
