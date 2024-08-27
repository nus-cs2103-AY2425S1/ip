public abstract class CommandFactory {
    public abstract Command parse(String text) throws GrayException;
}
