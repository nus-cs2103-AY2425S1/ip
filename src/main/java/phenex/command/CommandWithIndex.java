package phenex.command;

public abstract class CommandWithIndex extends Command {
    protected int index;

    public CommandWithIndex(int idx) {
        this.index = idx;
    }

    public void setIndex(int idx) {
        this.index = idx;
    }
}
