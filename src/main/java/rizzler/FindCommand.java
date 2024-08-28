package rizzler;

class FindCommand implements Command {
    private final String[] fullCommand;

    FindCommand(String[] fullCommand) throws RizzlerException {
        if (fullCommand.length < 2) {
            throw new RizzlerException("Please enter the string to find");
        }
        this.fullCommand = fullCommand;
    }

    public void execute(TaskList tasks,
                        Ui ui,
                        FileStorage fileStorage) throws RizzlerException {
        String keyword = this.fullCommand[1];
        for (int i = 2; i < this.fullCommand.length; i++) {
            keyword += " " + this.fullCommand[i];
        }
        try {
            tasks.find(keyword);
        } catch (RizzlerException e) {
            throw e;
        }
    }

    public boolean isExit() {
        return false;
    }
}
