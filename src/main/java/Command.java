public interface Command {
    String execute(ParsedInput parsedInput) throws BottyException;
}
