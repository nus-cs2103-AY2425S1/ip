public abstract class Command {
    String input;

    public Command(String input) {
        this.input = input;
    }

    public String getInput() {
        return this.input;
    }

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException;
}
