public class Command {
    private boolean exitChat;

    Command() {
        exitChat = false;
    }

    public boolean isExit() {
        return exitChat;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        Ui.say("Beeop... Unknown command");
    }
}
