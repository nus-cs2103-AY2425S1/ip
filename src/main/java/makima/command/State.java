package makima.command;

public abstract class State {

    public abstract String getResponse(String input, Makima makima);
}
