class MarkCommand implements Command {
    private final int markIndex;

    MarkCommand(String[] fullCommand) throws RizzlerException {
        if (fullCommand.length == 1) {
            throw new RizzlerException("Please key in the number of the task to mark\n"
                    + "Format:\n"
                    + "mark [number]");
        }
        try {
            this.markIndex = Integer.parseInt(fullCommand[1]) - 1;
        } catch (NumberFormatException e) {
            throw new RizzlerException("Please ensure that the argument after mark is a number\n"
                    + "Format:\n"
                    + "mark [number]");
        }
    }

    public void execute(TaskList tasks, Ui ui, FileStorage fileStorage) throws RizzlerException {
        try {
            tasks.mark(this.markIndex);
            fileStorage.save(tasks.getListToSave());
        } catch (RizzlerException e) {
            throw e;
        }
    }

    public boolean isExit() {
        return false;
    }
}
