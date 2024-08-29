package commands.parser;

public class MissingArgumentException extends RuntimeException {
    public MissingArgumentException(int expectedNoArgs, int actualNoArgs) {
        super(String.format(
            "Missing arguments for keyword. Expected %d but found %d arguments",
            expectedNoArgs,
            actualNoArgs
        ));
    }
}
