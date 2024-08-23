public class ExtraParamException extends IllegalInputException {
    public ExtraParamException(String offender) {
        super("Your instructions had too many parameters:",
                offender,
                "Please remove the extra parameters as required.");
    }
}
