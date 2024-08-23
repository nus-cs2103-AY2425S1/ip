public class MissingParamException  extends IllegalInputException {
    public MissingParamException (String offender) {
        super("Your instructions were missing some paramters:",
                offender,
                "Please fill in the required details as required.");
    }
}
