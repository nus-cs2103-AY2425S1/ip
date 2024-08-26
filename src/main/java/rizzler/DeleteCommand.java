package rizzler;

class DeleteCommand implements Command {
    private final int deleteIndex;

    DeleteCommand(String[] fullCommand) throws RizzlerException {
        if (fullCommand.length == 1) {
            throw new RizzlerException("Please key in the number of the task to delete\n"
                    + "Format:\n"
                    + "delete [number]");
        }
        try {
            this.deleteIndex = Integer.parseInt(fullCommand[1]) - 1;
        } catch (NumberFormatException e) {
            throw new RizzlerException("Please ensure that the argument after delete is a number\n"
                    + "Format:\n"
                    + "delete [number]");
        }
    }

    public void execute(TaskList tasks, Ui ui, FileStorage fileStorage) throws RizzlerException {
        try {
            tasks.delete(this.deleteIndex);
            fileStorage.save(tasks.getListToSave());
        } catch (RizzlerException e) {
            throw e;
        }
    }

    public boolean isExit() {
        return false;
    }
}
