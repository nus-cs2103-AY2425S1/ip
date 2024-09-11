package makima.command;

/**
 * Abstract state.
 */
public abstract class State {

    public abstract String getResponse(String input, Makima makima);
}
