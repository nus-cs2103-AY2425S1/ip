package rizzler;

class UnmarkCommand implements Command {
    private final int unmarkIndex;

    UnmarkCommand(String[] fullCommand) throws RizzlerException {
        if (fullCommand.length == 1) {
            throw new RizzlerException("Please key in the number of the task to unmark\n"
                    + "Format:\n"
                    + "unmark [number]");
        }
        try {
            this.unmarkIndex = Integer.parseInt(fullCommand[1]) - 1;
        } catch (NumberFormatException e) {
            throw new RizzlerException("Please ensure that the argument after unmark is a number\n"
                    + "Format:\n"
                    + "unmark [number]");
        }
    }

    public void execute(TaskList tasks, Ui ui, FileStorage fileStorage) throws RizzlerException {
        try {
            tasks.unmark(this.unmarkIndex);
            fileStorage.save(tasks.getListToSave());
        } catch (RizzlerException e) {
            throw e;
        }
    }

    public boolean isExit() {
        return false;
    }
}
