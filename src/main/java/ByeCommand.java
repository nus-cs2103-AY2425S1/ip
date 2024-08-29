/**
 * The ByeCommand class represents a command to exit Reminderebot.
 */
public class ByeCommand extends Command {
    /**
     * Instantiate a ByeCommand
     */
    public ByeCommand () {}

    /**
     * Saves data and exits Reminderebot
     * @param tasklist
     * @param ui
     * @param storage
     * @throws ReminderebotException
     */
    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        storage.saveData(tasklist);
        ui.goodbye();
    }

    /**
     * Bye exits Reminderebot.
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
