package phenex.command;

/**
 * Abstract class CommandWithIndex to represent a more specific Command with an index.
 */
public abstract class CommandWithIndex extends Command {
    protected int index;

    public CommandWithIndex(int idx) {
        this.index = idx;
    }

    public void setIndex(int idx) {
        this.index = idx;
    }

    @Override
    public boolean isTerminatingCommand() {
        return false;
    }
}
