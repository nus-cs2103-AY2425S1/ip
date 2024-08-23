public class UnknownCommandException extends IllegalInputException {
    public UnknownCommandException(String offender) {
        super("I'm unable to understand this instruction:",
                offender,
                "Please check for any typos.");
    }
}
